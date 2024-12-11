package com.liuyuncen.juc.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.rwlock
 * @author: Xiang想
 * @createTime: 2024-12-10  17:02
 * @description: TODO
 * @version: 1.0
 */
class MyResource{
    Map<String,String> map = new HashMap<>();
    Lock lock = new ReentrantLock();
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key,String value){
//        lock.lock();
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入");
            map.put(key,value);
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }finally {
            rwLock.writeLock().unlock();
        }
    }


    public String read(String key){
        String value = "";
//        lock.lock();
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            value = map.get(key);
//            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 读取完成");
        }finally {
//            lock.unlock();
            rwLock.readLock().unlock();
        }
        return value;
    }
}

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();




        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                new Thread(()->{
                    myResource.write(finalI +"",finalI+"");
                },String.valueOf(i)).start();
            }
        },"t1").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                new Thread(()->{
                    System.out.println(myResource.read(finalI+""));
                },String.valueOf(i)).start();
            }
        },"t2").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                new Thread(()->{
                    myResource.write(finalI +"",finalI+"");
                },String.valueOf(i)).start();
            }
        },"t3").start();
    }
}
