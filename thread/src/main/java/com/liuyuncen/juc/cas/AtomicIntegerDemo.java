package com.liuyuncen.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  13:41
 * @description: TODO
 * @version: 1.0
 */
class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}
public class AtomicIntegerDemo {

    public static final int SIZE = 50;

    public static void main(String[] args) throws Exception{
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try{
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() +"\t result \t" + myNumber.atomicInteger.get());
    }


    /**
     * @description: 一般只有测试环境才会这样搞
     * @author: Xiang想
     * @date: 2024/12/2 13:46
     **/
    public static void test(){
        MyNumber myNumber = new MyNumber();

        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myNumber.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName() +"\t result \t" + myNumber.atomicInteger.get());
    }
}
