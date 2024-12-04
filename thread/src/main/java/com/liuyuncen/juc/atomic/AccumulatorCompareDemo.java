package com.liuyuncen.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.atomic
 * @author: Xiang想
 * @createTime: 2024-12-03  11:58
 * @description: TODO
 * @version: 1.0
 */
class ClickNumber{
    int number = 0;
    public synchronized void clickBySynchronized(){
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong(){
        atomicLong.getAndIncrement();
    }


    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder(){
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
    public void clickByLongAccumulator(){
        longAccumulator.accumulate(1);
    }


}
public class AccumulatorCompareDemo {
    public static final int _1W = 10000;
    public static final int threadNumber = 50;

    public static void main(String[] args) throws Exception{
        ClickNumber clickNumber = new ClickNumber();
        long startTime ;
        long endTime ;

        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);


        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                }finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("synchronized 方法耗时 ："+ (endTime-startTime) +" ms");


        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                }finally {
                    countDownLatch2.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("atomicLong 方法耗时 ："+ (endTime-startTime) +" ms");


        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                }finally {
                    countDownLatch3.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("longAdder 方法耗时 ："+ (endTime-startTime) +" ms");


        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                }finally {
                    countDownLatch4.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("longAccumulator 方法耗时 ："+ (endTime-startTime) +" ms");
    }
}
