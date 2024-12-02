package com.liuyuncen.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个自旋锁，复习CAS思想
 * 自旋锁好处：循环比较获取没有类似 wait 阻塞
 *
 * 通过 CAS 操作完成自旋锁，A线程 先进来调用 myLock 方法自己持有锁 5 秒钟，B 随后进来后发现
 * 当前有线程持有锁，所以只能通过自旋等待，直到 A 释放锁后 B 随后抢到
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  11:50
 * @description: TODO
 * @version: 1.0
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t ---- come in, lock");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() +"\t ---- task over,unLock");

    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.lock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.unLock();
        },"A").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(()->{
            spinLockDemo.lock();
            spinLockDemo.unLock();
        },"B").start();

    }
}
