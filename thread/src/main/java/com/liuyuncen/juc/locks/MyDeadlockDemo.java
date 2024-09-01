package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * <a href="https://github.com/YuncenLiu/code-example/blob/master/thread/src/main/java/com/liuyuncen/juc/locks/MyDeadlockDemo.java">死锁案例</a>
 * <p>1. 系统资源不足、进程运行推进顺序不合适、资源分配不得当</p>
 */
public class MyDeadlockDemo {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();


        new Thread(()->{
            synchronized (object1){
                System.out.println(Thread.currentThread().getName() + "\t1 自己持有 object1 锁，希望获取 object2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (object2){
                    System.out.println(Thread.currentThread().getName() + "\t 获取 object2 锁成功");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (object2){
                System.out.println(Thread.currentThread().getName() + "\t1 自己持有 object2 锁，希望获取 object1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (object1){
                    System.out.println(Thread.currentThread().getName() + "\t 获取 object1 锁成功");
                }
            }
        },"t2").start();
    }
}
