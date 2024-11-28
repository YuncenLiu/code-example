package com.liuyuncen.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.interrupt
 * @author: Xiang想
 * @createTime: 2024-11-28  13:02
 * @description: TODO
 * @version: 1.0
 */
public class InterruptDemo3 {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t --- \t" + "中断标志位 \t" + Thread.currentThread().isInterrupted() + "\t 程序停止");
                    break;
                }

                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {
                    // 为什么要在异常处再调用一次
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("\t ---- hello InterruptDemo3");
            }
        }, "t1");
        t1.start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(()->{
            t1.interrupt();
        },"t2").start();
    }
}
