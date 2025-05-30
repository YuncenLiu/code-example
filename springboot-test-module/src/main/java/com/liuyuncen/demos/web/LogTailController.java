package com.liuyuncen.demos.web;

import com.liuyuncen.demos.entity.LogFileTracker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.demos.web
 * @author: Xiang想
 * @createTime: 2025-05-30  15:36
 * @description: TODO
 * @version: 1.0
 */
@RestController
public class LogTailController {
    // 存储每个日志文件的读取状态
    private final Map<String, LogFileTracker> fileTrackerMap = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    // 日志文件根目录（根据实际需求修改）
    private static final String LOG_BASE_DIR = "/Users/xiang/Desktop/cloud/logs/";

    @GetMapping(value = "/logs/tail/{logFileName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter tailLogFile(@PathVariable String logFileName) throws IOException {
        String filePath = LOG_BASE_DIR + logFileName;
        File logFile = new File(filePath);

        if (!logFile.exists() || !logFile.isFile()) {
            throw new IllegalArgumentException("日志文件不存在: " + logFileName);
        }

//        SseEmitter emitter = new SseEmitter(5 * 60 * 1000L); // 5分钟超时
        SseEmitter emitter = new SseEmitter(10 * 1000L); // 5分钟超时
        LogFileTracker tracker = fileTrackerMap.computeIfAbsent(filePath, k -> new LogFileTracker(filePath));

        // 提交日志跟踪任务
        executor.execute(() -> {
            try {
                // 第一步：发送整个文件内容
                sendEntireFile(emitter, tracker);

                // 第二步：开始跟踪新增内容
                trackNewContent(emitter, tracker);
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        // 清理资源
        emitter.onCompletion(() -> tracker.removeEmitter(emitter));
        emitter.onTimeout(() -> tracker.removeEmitter(emitter));
        emitter.onError((ex) -> tracker.removeEmitter(emitter));

        return emitter;
    }

    private void sendEntireFile(SseEmitter emitter, LogFileTracker tracker) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(tracker.getFilePath(), "r")) {
            long fileLength = file.length();
            long position = 0;

            // 读取整个文件内容
            while (position < fileLength) {
                file.seek(position);
                String line = file.readLine();
                if (line != null) {
                    // 转换编码为UTF-8
                    String text = new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    emitter.send(SseEmitter.event().data(text));
                    position = file.getFilePointer();
                } else {
                    // 没有更多内容
                    break;
                }
            }

            // 更新跟踪器位置到文件末尾
            tracker.setLastPosition(position);
        }
    }

    private void trackNewContent(SseEmitter emitter, LogFileTracker tracker) {
        try (RandomAccessFile file = new RandomAccessFile(tracker.getFilePath(), "r")) {
            file.seek(tracker.getLastPosition());
            tracker.addEmitter(emitter);

            while (!Thread.currentThread().isInterrupted()) {
                // 处理日志轮转
                if (file.length() < tracker.getLastPosition()) {
                    tracker.setLastPosition(0);
                    file.seek(0);
                }

                String line = file.readLine();
                if (line != null) {
                    // 转换编码为UTF-8
                    String text = new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    tracker.setLastPosition(file.getFilePointer());
                    emitter.send(SseEmitter.event().data(text));
                } else {
                    Thread.sleep(1000); // 没有新内容时暂停1秒
                }
            }
        } catch (IOException | InterruptedException e) {
            emitter.completeWithError(e);
        } finally {
            tracker.removeEmitter(emitter);
        }
    }

}
