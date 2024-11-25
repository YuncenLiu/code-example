package com.liuyuncen.juc.cf1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  15:02
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureCombineDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 over");
            return 10;
        });


        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 启动");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2 over");
            return 20;
        });

        Integer result = task1.thenCombine(task2, (r1, r2) -> {
            return r1 + r2;
        }).join();

        System.out.println("result = " + result);


        System.out.println("-----------------------------------------------");

        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 1启动");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 2启动");
            return 20;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 3启动");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 4启动");
            return 30;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- 5启动");
            return x + y;
        });

        System.out.println("task3 = " + task3.join());


        System.out.println("-----------------------------------------------");
    }
}
