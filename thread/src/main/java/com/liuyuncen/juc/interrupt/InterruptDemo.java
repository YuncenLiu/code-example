package com.liuyuncen.juc.interrupt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo {

    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted 被修改为 true，程序中止");
                    break;
                }
                System.out.println("----- hello isInterrupted");
            }
        }, "t1");
        t1.start();

        TimeUnit.MILLISECONDS.sleep(1);
//        new Thread(()->{
//            t1.interrupt();
//        },"t2").start();
        t1.interrupt();

    }

    private static void m2_atomicBoolean() throws InterruptedException {
        new Thread(()->{
            while (true){
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为 true，程序中止");
                    break;
                }
                System.out.println("----- hello volatile");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t1").start();

        TimeUnit.SECONDS.sleep(5);
        atomicBoolean.set(true);
    }

    private static void m1_volatile() throws InterruptedException {
        new Thread(()->{
            while (true){
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为 true，程序中止");
                    break;
                }
                System.out.println("----- hello volatile");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t1").start();

        TimeUnit.SECONDS.sleep(5);
        isStop = true;
    }
}
