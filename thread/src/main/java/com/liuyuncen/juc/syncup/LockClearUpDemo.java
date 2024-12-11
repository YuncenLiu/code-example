package com.liuyuncen.juc.syncup;

import org.openjdk.jol.info.ClassLayout;

public class LockClearUpDemo {

    static Object objectLock = new Object();

    public void m1(){
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void main(String[] args) {
        LockClearUpDemo lock = new LockClearUpDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                lock.m1();
            },String.valueOf(i)).start();
        }

    }
}
