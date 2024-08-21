package com.liuyuncen.juc.cf;

import java.util.concurrent.*;

/** 3个任务，目前只有一个线程 main 来处理
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-21  13:42
 * @description: TODO
 * @version: 1.0
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        m2();
        long endTime = System.currentTimeMillis();
        System.out.println("--- cosTime:"+ (endTime-startTime) + " ms");
    }


    private static void m2() throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });

        FutureTask<String> futureTask2 = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });

        FutureTask<String> futureTask3 = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "task3 over";
        });

        // new 对象容易造成垃圾, 使用线程池
//        Thread t1 = new Thread(futureTask1, "t1");
//        t1.start();

        threadPool.submit(futureTask1);
        threadPool.submit(futureTask2);
        threadPool.submit(futureTask3);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());

        threadPool.shutdown();
    }




    private static void m1() throws InterruptedException {


        TimeUnit.MILLISECONDS.sleep(500);
        TimeUnit.MILLISECONDS.sleep(300);
        TimeUnit.MILLISECONDS.sleep(300);


    }
}
