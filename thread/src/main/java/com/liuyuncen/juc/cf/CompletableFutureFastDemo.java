package com.liuyuncen.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  14:45
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureFastDemo {
    public static void main(String[] args) {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A Come in");
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B Come in");
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            return "playB";
        });

        CompletableFuture<String> result = playA.applyToEither(playB, f -> {
            return f + " is winer";
        });
        System.out.println(Thread.currentThread().getName() + "\n ------- " + result.join());
    }
}
