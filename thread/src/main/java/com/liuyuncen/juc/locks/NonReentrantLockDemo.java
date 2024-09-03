package com.liuyuncen.juc.locks;

import java.util.concurrent.Semaphore;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.locks
 * @author: Xiang想
 * @createTime: 2024-09-03  11:57
 * @description: TODO
 * @version: 1.0
 */
public class NonReentrantLockDemo {
    private final Semaphore semaphore = new Semaphore(1);

    public void lock() throws InterruptedException {
        semaphore.acquire();
    }

    public void unlock() {
        semaphore.release();
    }

    public static void main(String[] args) {
        NonReentrantLockDemo lock = new NonReentrantLockDemo();

        Runnable task = () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 1- getLock");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 2- getLock Again");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 3- del Lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 4- del Lock Again");
            }
        };

        Thread t1 = new Thread(task);
        t1.start();
    }
}
