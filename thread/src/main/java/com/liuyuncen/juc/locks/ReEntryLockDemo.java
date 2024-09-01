package com.liuyuncen.juc.locks;

public class ReEntryLockDemo {

    /*
    synchronized 天生的重入锁，隐型的
     */
    public static void main(String[] args) {
        final Object object = new Object();

        new Thread(() ->{
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "\t 1调用");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() + "\t 2调用");
                    synchronized (object){
                        System.out.println(Thread.currentThread().getName() + "\t 3调用");
                    }
                }
            }
        },"t1").start();
    }
}
