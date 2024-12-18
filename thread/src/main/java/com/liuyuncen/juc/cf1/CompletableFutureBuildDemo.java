package com.liuyuncen.juc.cf1;

import java.util.concurrent.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-21  17:52
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
/*

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, threadPool);
        System.out.println(completableFuture.get());
        System.out.println("----- hello ");
*/

        CompletableFuture<String> completableFuture  = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello SupplyAsync";
        },threadPool);

        System.out.println(completableFuture.get());

        // 没有关闭会导致程序挂起
        threadPool.shutdown();
    }
}
