package com.liuyuncen.juc.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.LockSupport
 * @author: Xiang想
 * @createTime: 2024-11-28  14:59
 * @description: TODO
 * @version: 1.0
 */
public class LockSupportDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t --- come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t --- 被唤醒");

        }, "t1");
        t1.start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t --- 唤醒通知");
        }).start();
    }
}
