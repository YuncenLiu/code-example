package com.liuyuncen.juc.cf2;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 功能描述：Demo-01
 *
 * @author: Xiang
 * @date: 2024年11月25日 22:53:33
 */
public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws Exception {

        System.out.println("----　没有指定线程池　ForkJoinPool.commonPool");
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
        });
        System.out.println(completableFuture.get());


        System.out.println("----　使用自定义线程池 pool-1-thread");
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
         CompletableFuture<Void> definePoolFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
        },threadPool);
        System.out.println(definePoolFuture.get());
        threadPool.shutdown();


        System.out.println("---  使用有返回值");
         CompletableFuture<String> returnFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
            return "hello supplyAsync";
        });
        System.out.println(returnFuture.get());


        System.out.println("---  使用自定义有返回值");
         ExecutorService returnPool = Executors.newFixedThreadPool(3);
         CompletableFuture<String> definePoolReturnFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) {e.printStackTrace();}
            return "hello supplyAsync";
        },returnPool);
        System.out.println(definePoolReturnFuture.get());
        returnPool.shutdown();
    }
}
