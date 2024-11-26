package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 两个静态同步方法，两部手机
 *
 */
public class Lock8Demo6 {
    public static void main(String[] args) {
        Phone6 phone = new Phone6();
        Phone6 phone2 = new Phone6();
        new Thread(()->{
            phone.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            phone2.sendSMS();
        },"b").start();
    }
}

class Phone6{
    public static synchronized void sendMail(){
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("---- sendMail");
    }
    public static synchronized void sendSMS(){
        System.out.println("---- sendSMS");
    }

    public void hello(){
        System.out.println("---- hello");
    }
}
