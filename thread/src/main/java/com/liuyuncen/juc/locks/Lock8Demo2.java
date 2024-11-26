package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 此时Mail加入3秒延迟
 * 请问此时先打印邮件，还是打印 SMS？
 *
 */
public class Lock8Demo2 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            phone2.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            phone2.sendSMS();
        },"b").start();
    }
}

class Phone2{
    public synchronized void sendMail(){
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("---- sendMail");
    }
    public synchronized void sendSMS(){
        System.out.println("---- sendSMS");
    }

}
