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
            stampedLock.unlock(stamp);
        }
    }


    // 乐观读
    public void optimisticRead(){
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        // 故意间隔4秒，乐观认为读取中没有其他线程修改 number 值

        System.out.println("4秒前 stampedLock.validate 方法值(true 无修改，false 有修改) \t" + stampedLock.validate(stamp) );
        for (int i = 0; i < 4; i++) {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 正在读取 ..... " + i +
                    " \t 秒后， stampedLock.validate 方法值(true 无修改，false 有修改) \t" + stampedLock.validate(stamp));
        }
        if (!stampedLock.validate(stamp)){
            System.out.println("有人修改过-----有操作");
            stamp = stampedLock.readLock();
            try {
                System.out.println("从乐观读 升级为 悲观读");
                result = number;
                System.out.println("重新悲观读");
            }finally {
                stampedLock.unlock(stamp);
            }

        }
        System.out.println(Thread.currentThread().getName() +"\t finally value" + result);


    }

    public static void main(String[] args) {
        func2();
    }

    public static void func2(){
        StampedLockDemo resource = new StampedLockDemo();
        new Thread(()->{
            resource.optimisticRead();
        },"readThread").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t --- come in");
            resource.write();
        },"writeThread").start();
    }

    public static void func1(){
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
