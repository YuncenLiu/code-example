package com.liuyuncen.juc.cf2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf2
 * @author: Xiang想
 * @createTime: 2024-11-26  11:43
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
    }
}
