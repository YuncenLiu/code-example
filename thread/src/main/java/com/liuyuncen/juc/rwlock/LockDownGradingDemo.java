package com.liuyuncen.juc.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级：遵循获取写锁，再获取读锁，在释放写锁顺序，写锁能够降级为读锁
 * 如果应该线程占有了写锁，在不释放写锁的情况下，他还能占用读锁，即使锁降级为了读锁
 * 读没有完成时，写锁无法获取，必须要等着读锁完成后才有机会。
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.rwlock
 * @author: Xiang想
 * @createTime: 2024-12-11  13:09
 * @description: TODO
 * @version: 1.0
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


        /*
        readLock.lock();
        System.out.println("---- 读取");
        readLock.unlock();


        writeLock.lock();
        System.out.println("---- 写入");
        writeLock.unlock();
        */


        readLock.lock();
        System.out.println("---- 读取");

        writeLock.lock();
        System.out.println("---- 写入");


        writeLock.unlock();
        readLock.unlock();


    }
}
