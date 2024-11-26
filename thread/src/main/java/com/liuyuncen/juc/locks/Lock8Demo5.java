package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 两个静态同步方法，一部手机
 *
 */
public class Lock8Demo5 {
    public static void main(String[] args) {
        Phone5 phone5 = new Phone5();
        new Thread(()->{
            phone5.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            phone5.sendSMS();
        },"b").start();
    }
}

class Phone5{
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
