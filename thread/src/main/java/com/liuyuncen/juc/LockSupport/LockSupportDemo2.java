package com.liuyuncen.juc.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.LockSupport
 * @author: Xiang想
 * @createTime: 2024-11-28  14:22
 * @description: TODO
 * @version: 1.0
 */
public class LockSupportDemo2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t --- come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t --- 被唤醒");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1").start();



        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t --- 唤醒操作");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }


}
