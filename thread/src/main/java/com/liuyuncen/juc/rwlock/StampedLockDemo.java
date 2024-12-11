package com.liuyuncen.juc.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.rwlock
 * @author: Xiang想
 * @createTime: 2024-12-11  15:00
 * @description: TODO
 * @version: 1.0
 */
public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write(){
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t 写线程准备修改...");
        try {
            number++;
        }finally {
            stampedLock.unlock(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "\t 写线程修改完成!");
    }

    // 悲观读
    public void read(){
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t 读线程准备读取...");

        for (int i = 0; i < 4; i++) {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 读线程正在读取中.....");
        }

        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t 读线程获取成员变量 \t" + result);
            System.out.println("写线程没有修改成功，读锁时写锁无法介入，传统读写互斥");
        } finally {

        }
    }


    // 乐观读
    public void optimisticRead(){

    }

    public static void main(String[] args) {
        StampedLockDemo resource = new StampedLockDemo();
        new Thread(()->{
            resource.read();
        },"readThread").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t --- come in");
            resource.write();
        },"writeThread").start();
    }
}
