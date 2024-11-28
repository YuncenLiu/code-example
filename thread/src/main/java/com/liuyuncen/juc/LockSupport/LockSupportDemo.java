package com.liuyuncen.juc.LockSupport;

import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.LockSupport
 * @author: Xiang想
 * @createTime: 2024-11-28  14:03
 * @description: TODO
 * @version: 1.0
 */
public class LockSupportDemo {
    public static void main(String[] args) {

//        System.out.println(System.getProperty("java.version"));
//        String s = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
//        System.out.println(s);
        Object objectLock = new Object();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + "\t --- come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t --- 被唤醒");
            }
        },"t1").start();


        new Thread(() ->{
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t --- 发出通知");
            }
        },"t2").start();
    }
}
