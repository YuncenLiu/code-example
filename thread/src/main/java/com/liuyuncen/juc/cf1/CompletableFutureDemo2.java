package com.liuyuncen.juc.cf1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  12:00
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Step 1");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 200;
        });
        System.out.println("Step 2");
        System.out.println(" ----- " +completableFuture.get());
        System.out.println("Step 3");
        System.out.println(completableFuture.complete(404) + " ----- " +completableFuture.get());
        System.out.println("Step 4");
    }
}
