package com.liuyuncen.juc.cf1;

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
//            if (result > 0) {
//                int i = 10/0;
//            }
            return result;
        }).whenComplete((v,e) ->{
            // 无论是否异常，都会执行这里面当方法
            System.out.println("--- 计算完成，无论是否异常都会打印: "+ v);
            if (null == e) {
                // 只有非异常情况，才会走这里面
                System.out.println("--- 计算完成，更新系统 updateValue: "+ v);
            }

        }).exceptionally(e -> {
            // 当出现异常时候，会执行这里当方法

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
