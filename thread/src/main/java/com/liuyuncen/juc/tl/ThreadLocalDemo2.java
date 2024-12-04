package com.liuyuncen.juc.tl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.tl
 * @author: Xiang想
 * @createTime: 2024-12-03  17:53
 * @description: TODO
 * @version: 1.0
 */
class MyData{
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);
    public void add(){
        threadLocal.set( 1 + threadLocal.get());
    }
}
public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(()->{
                    Integer beforeInt = myData.threadLocal.get();
                    myData.add();
                    Integer afterInt = myData.threadLocal.get();
                    String msg = String.format("%s \t before: %s , after： %s",Thread.currentThread().getName(), beforeInt, afterInt);
                    System.out.println(msg);
                });
            }
        }finally {
            threadPool.shutdown();
        }

    }
}
