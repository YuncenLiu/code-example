package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 请问先打印邮件，还是打印 SMS？
 * 答：a
 *
 */
public class Lock8Demo1 {
    public static void main(String[] args) {
        Phone1 phone1 = new Phone1();
        new Thread(()->{
            phone1.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            phone1.sendSMS();
        },"b").start();
    }
}

class Phone1{
    public void sendMail(){
        // 严格遵守手册，能锁代码块就不锁方法
        synchronized (this){
            System.out.println("---- sendMail");
        }
    }
    public synchronized void sendSMS(){
        System.out.println("---- sendSMS");
    }

}
