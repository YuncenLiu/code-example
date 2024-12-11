package com.liuyuncen.juc.syncup;

import org.openjdk.jol.info.ClassLayout;

public class LockBigDemo {
    static Object objectLock = new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (objectLock){
                System.out.println("111");
                System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
            }
            synchronized (objectLock){
                System.out.println("222");
                System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
            }
            synchronized (objectLock){
                System.out.println("333");
                System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
            }
            synchronized (objectLock){
                System.out.println("444");
                System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
            }

        },"t1").start();
    }
}
