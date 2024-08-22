package com.liuyuncen.juc.cf;

import java.util.concurrent.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  12:00
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenAccept((r -> {
            // 消费形方法
            System.out.println(r);

            // 以另种写法，但是这里已经拿不到数据了
        })).thenAccept(System.out::println);


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
