package com.liuyuncen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-18  13:27
 * @description: TODO
 * @version: 1.0
 */
public class IntervalThread implements Runnable{

    private Timer timer;
    private TimerTask task;
    private int intervalInSeconds;

    private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public IntervalThread(int intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    @Override
    public void run() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                // 这里是定时执行的任务逻辑
                System.out.println(sim.format(new Date()) + "Thread is running!");
            }
        };
        timer.schedule(task, 0, intervalInSeconds * 1000);

        Timer listen = new Timer();
        TimerTask listenTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("监视器每秒执行:" + sim.format(new Date()));
            }
        };
        // 每秒输出时间
        listen.schedule(listenTask,0,1000);
    }

    public void stop() {
        if (timer != null) {
            System.out.println("timer 停止！");
            timer.cancel();
        }
        if (task != null) {
            System.out.println("task 停止！");
            task.cancel();
        }
    }

}
