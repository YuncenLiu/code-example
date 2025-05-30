package com.liuyuncen.demos.entity;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.demos.entity
 * @author: Xiang想
 * @createTime: 2025-05-30  15:37
 * @description: TODO
 * @version: 1.0
 */
public class LogFileTracker {
    private final String filePath;
    private long lastPosition;
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public LogFileTracker(String filePath) {
        this.filePath = filePath;
        this.lastPosition = new File(filePath).length(); // 默认从文件末尾开始
    }

    public synchronized void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
    }

    public synchronized void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
        if (emitters.isEmpty()) {
            // 当没有监听器时重置位置（可选）
            lastPosition = new File(filePath).length();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public long getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(long lastPosition) {
        this.lastPosition = lastPosition;
    }

    public CopyOnWriteArrayList<SseEmitter> getEmitters() {
        return emitters;
    }
}
