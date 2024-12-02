package com.liuyuncen.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  13:07
 * @description: TODO
 * @version: 1.0
 */
public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {


    }

    /**
     * @description: 有效解决 ABA 问题
     * @author: Xiang想
     * @date: 2024/12/2 13:22
     **/
    public static void stampedReference() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" +
                    stampedReference.compareAndSet(100, 101, 1, 2) + "\t" +
                    stampedReference.getReference() + "\t" +
                    stampedReference.getStamp());
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" +
                    stampedReference.compareAndSet(101, 100, 2, 3) + "\t" +
                    stampedReference.getReference() + "\t" +
                    stampedReference.getStamp());
        }, "t1").start();


        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" +
                    stampedReference.compareAndSet(100, 2022, 3, 4) + "\t" +
                    stampedReference.getReference() + "\t" +
                    stampedReference.getStamp());
        }, "t2").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stampedReference.getReference() + "\t" +
                stampedReference.getStamp());

    }


    /**
     * @description: 复现ABA问题
     * @author: Xiang想
     * @date: 2024/12/2 13:22
     **/
    public static void atomicIntegerFunc() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + atomicInteger.compareAndSet(100, 101) + "\t" + atomicInteger.get());
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + atomicInteger.compareAndSet(101, 100) + "\t" + atomicInteger.get());
        }, "t1").start();


        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + atomicInteger.compareAndSet(100, 2022) + "\t" + atomicInteger.get());
        }, "t2").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());
    }
}
