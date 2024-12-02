package com.liuyuncen.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  14:16
 * @description: TODO
 * @version: 1.0
 */
public class AtomicMarkableReferenceDemo {

    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100,false);


    public static void main(String[] args) {
        new Thread(()->{
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 默认标识： \t" + marked);

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 1秒后标识： \t" + marked);
            markableReference.compareAndSet(100,200,marked,!marked);
            System.out.println(Thread.currentThread().getName() + "\t 1秒后操作标识： \t" + marked);
            System.out.println(Thread.currentThread().getName() + "\t 1秒后操作数： \t" + markableReference.getReference());

        },"t1").start();


        new Thread(()->{
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 默认标识： \t" + marked);
            boolean b = markableReference.compareAndSet(100, 300, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t result \t" + b);
            System.out.println(Thread.currentThread().getName() + "\t marked \t" + markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t reference \t" + markableReference.getReference());
        },"t2").start();
    }
}
