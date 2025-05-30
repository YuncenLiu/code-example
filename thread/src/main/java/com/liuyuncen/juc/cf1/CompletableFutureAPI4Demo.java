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
public class CompletableFutureAPI4Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 各玩各的，没有任何关系
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {System.out.println("没有任何关系");}).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "===").join());

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {
            System.out.println("没有任何关系");
        }).join());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
