package com.liuyuncen.juc.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-21  15:17
 * @description: TODO
 * @version: 1.0
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName() + "\t ---- come in");
            TimeUnit.SECONDS.sleep(5);
            return Thread.currentThread().getName();
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        // 只要调用我，我就截停当前线程，等我执行完，我给你返回结果
//        System.out.println(futureTask.get());

        // 3秒钟没给我，直接抛出 TimeoutException 异常
//        System.out.println(futureTask.get(3,TimeUnit.SECONDS));
        System.out.println(Thread.currentThread().getName() + "\t ----- 忙其他任务");

        while (true){
            if (futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在运行中.....");
            }
        }
    }
}
