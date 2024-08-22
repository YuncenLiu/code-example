package com.liuyuncen.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  09:37
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ----- come in ");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" ----- 2 秒后出结果 ---- " + result);
            return result;
        }).whenComplete((v,e) ->{
            if (null == e) {
                System.out.println("--- 计算完成，更新系统 updateValue: "+ v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("异常情况"+e.getCause() + " ---- " + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName()  + " 线程去干其他事情了 ");

        TimeUnit.SECONDS.sleep(5);
    }

    private static void future1()  throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ----- come in ");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" ----- 2 秒后出结果 ---- " + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName()  + " 线程去干其他事情了 ");
        System.out.println(completableFuture.get());
    }
}
