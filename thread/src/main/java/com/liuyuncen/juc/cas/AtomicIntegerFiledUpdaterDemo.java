package com.liuyuncen.juc.cas;

import lombok.Getter;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @belongsProject: 测试平台
 * 以一种线程安全的方式操作非线程安全对象的某个字段，
 *
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  14:44
 * @description: TODO
 * @version: 1.0
 */
@Getter
class BankAccount{
    String bankName = "CCB";
    volatile int money = 0;

    public synchronized  void add(){
        money++;
    }

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");

    // 不加 Sync 还要保证高性能、原子性
    public void transMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }
}
public class AtomicIntegerFiledUpdaterDemo  {
    public static void main(String[] args) throws Exception{
        long startTime = new Date().getTime();
        for (int i = 0; i < 100; i++) {
            atomicIntegerFiledUpdaterFunc();
        }
        long endTime = new Date().getTime();

        System.out.println(" 结束耗费时间：" +( endTime - startTime) + "ms");
    }

    public static void atomicIntegerFiledUpdaterFunc() throws Exception{
        long startTime = new Date().getTime();

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.transMoney(bankAccount);
                    }
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t resoult:" +bankAccount.getMoney() );
        long endTime = new Date().getTime();

        System.out.println("耗费时间：" +( endTime - startTime) + "ms");
    }

    public static void syncFunc() throws Exception{
        long startTime = new Date().getTime();

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.add();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t resoult:" +bankAccount.getMoney() );
        long endTime = new Date().getTime();

        System.out.println("耗费时间：" +( endTime - startTime) + "ms");
    }
}
