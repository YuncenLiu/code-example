package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 一个静态一个普通，2部手机
 *
 */
public class Lock8Demo8 {
    public static void main(String[] args) {
        Phone8 phone = new Phone8();
        Phone8 phone2 = new Phone8();
        new Thread(()->{
            phone.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            phone2.sendSMS();
        },"b").start();
    }
}

class Phone8{
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
