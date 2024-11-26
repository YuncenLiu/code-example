package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 一个静态一个普通，一部手机
 *
 */
public class Lock8Demo7 {
    public static void main(String[] args) {
        Phone7 phone7 = new Phone7();
        new Thread(()->{
            phone7.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            phone7.sendSMS();
        },"b").start();
    }
}

class Phone7{
    public static synchronized void sendMail(){
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("---- sendMail");
    }
    public synchronized void sendSMS(){
        System.out.println("---- sendSMS");
    }

    public void hello(){
        System.out.println("---- hello");
    }
}
