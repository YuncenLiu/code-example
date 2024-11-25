package com.liuyuncen.juc.cf1;

import java.util.concurrent.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  12:00
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureAPI2Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("----- 111");
            return 1;
        }, threadPool).handle((v, e) -> {
            System.out.println("----- 222");
//            int i = 10 / 0;
            return v + 2;
        }).handle((v, e) -> {
            System.out.println("----- 333");
            return v + 3;
        }).thenApply(v -> {
            System.out.println("----- 444");
            return v + 4;
        }).whenComplete((v, e) -> {
            if (null == e) {
                System.out.println("--- 计算完成, " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("--- 异常情况: " + e.getCause() + " ---- " + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " ---- 主线程忙其他的事情去了 ");
        TimeUnit.SECONDS.sleep(5);
        threadPool.shutdown();
    }
}
