package com.liuyuncen.juc.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyDeadlockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t 获取到锁");
        },"t1").start();

        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t 获取到锁");
        },"t2").start();

    }
}
