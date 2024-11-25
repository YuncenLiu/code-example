package com.liuyuncen.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.base
 * @author: Xiang想
 * @createTime: 2024-11-25  15:35
 * @description: TODO
 * @version: 1.0
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 开始运行，" + (Thread.currentThread().isDaemon()? "守护线程":"用户线程"));
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("5 秒后，t1 结束");
        }, "t1");
        t1.setDaemon(false);
        t1.start();
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() +  "\t ---- end 主线程结束");
    }
}
