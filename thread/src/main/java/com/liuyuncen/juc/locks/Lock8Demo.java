package com.liuyuncen.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 1、标准访问ab两个线程，请问先打印 mail 还是 sms
 * 2、mail 暂停3秒，请问谁先打印
 * 3、添加一个普通的 hello 方法，先打印谁
 * 4、两个对象，谁先打印
 * 5、一个对象，mail 和 sms 都是静态方法，谁先打印
 * 6、两个对象，一个静态、一个普通，谁先打印
 * 7、一个对象，一个静态、一个普通方法，谁先打印
 *
 * 1-2 一个
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.locks
 * @author: Xiang想
 * @createTime: 2024-08-22  15:41
 * @description: TODO
 * @version: 1.0
 */
public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone1.sendEmail();
        }, "t1").start();

        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone1.sendSms();
        }, "t2").start();

    }
}

class Phone {
    public synchronized void sendEmail(){
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("------ sendEmail ");
    }

    public synchronized void sendSms(){
        System.out.println("------ sendSms ");
    }

    public void sayHello(){
        System.out.println("------ sayHello ");
    }
}