package com.liuyuncen.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  13:28
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureWithThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
            return "abcd";
        },threadPool).thenRunAsync(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("5号任务" + "\t" + Thread.currentThread().getName());
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println("----- 1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("----- 2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("----- 3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("----- 4");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("----- 5");
        TimeUnit.SECONDS.sleep(6);
        threadPool.shutdown();
    }
}
