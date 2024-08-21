package com.liuyuncen.juc.cf;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-21  11:52
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread2());
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(futureTask.get());

    }
}

/**
 * @description: Runnable 没有返回值
 * @author: Xiang想
 * @date: 2024/8/21 11:53
 **/
class MyThread implements Runnable{
    @Override
    public void run(){

    }
}


/**
 * @description: Callable 有返回值，能抛异常
 * @author: Xiang想
 * @date: 2024/8/21 11:53
 **/
class MyThread2 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("---- come in call() ");
        return "hello Callable";
    }
}
