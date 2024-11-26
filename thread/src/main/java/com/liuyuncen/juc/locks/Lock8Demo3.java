package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 此时Mail加入3秒延迟
 * 再添加一个普通的hello方法
 * 请问此时先打印邮件，还是打印 hello？
 *
 */
public class Lock8Demo3 {
    public static void main(String[] args) {
        Phone3 phone3 = new Phone3();
        new Thread(()->{
            phone3.sendMail();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            phone3.hello();
        },"b").start();
    }
}

class Phone3{
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
