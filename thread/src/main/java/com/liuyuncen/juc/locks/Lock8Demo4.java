package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 两部手机，先打印邮件还是打印短信
 *
 */
public class Lock8Demo4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(()->{
            phone.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            phone2.sendSMS();
        },"b").start();
    }
}

class Phone4{
    public synchronized void sendMail(){
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
