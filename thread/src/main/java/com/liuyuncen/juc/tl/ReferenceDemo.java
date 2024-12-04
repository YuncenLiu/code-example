package com.liuyuncen.juc.tl;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试 强、软、弱、虚 四种引用类型
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.tl
 * @author: Xiang想
 * @createTime: 2024-12-04  11:04
 * @description: TODO
 * @version: 1.0
 */
class MyObject{

    // 这个方法一般不复写
    @Override
    protected void finalize() throws Throwable{
        // finalize 的通常目的是在对象被不可撤销的丢弃之前执行清理操作
        System.out.println(Thread.currentThread().getName() + "\t 啊！ 我被杀掉了");
        super.finalize();
    }
}
public class ReferenceDemo {


    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();

        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        System.out.println("phantomReference.get() \t" + phantomReference.get());

        List<byte[]> list = new ArrayList<>();
        new Thread(()->{
            while (true){
                list.add(new byte[1 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("每次添加之后获取 \t" + phantomReference.get());
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null){
                    System.out.println("---- 有虚对象加入了队列");
                }
            }
        },"t2").start();

    }


    // 弱引用 不管够不够用，任何风吹草动，都干掉
    private static void weakReference(){
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before 内存够用\t" + weakReference);
        System.gc();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("gc after 内存够用\t" + weakReference);
    }


    // 够用的时候， 对象不会被干掉，如果不够用，则对象会被回收
    private static void softReference(){
        SoftReference<MyObject> softReference = new SoftReference<MyObject>(new MyObject());
        System.out.println("softReference \t" +softReference);

        System.gc();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("gc after 内存够用\t" +  softReference.get());

        try {
            // 如果 设置 JVM启动参数 -Xms10m -Xmx10m，内存不够对象会被杀掉，如果没有设置，内存够，对象不会被杀 finalize 方法不会被调用
            byte[] bytes = new byte[20 * 1024 * 1024]; // 20M 对象
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("gc after 内存不够\t" + softReference.get());
        }
    }


    // 强引用
    private static void strongReference(){
        MyObject myObject = new MyObject();
        System.out.println("gc before  \t " + myObject);
        System.gc();
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("gc after 1 \t " + myObject);

        // 实例对象被清理
        myObject = null;
        // 主动触发
        System.gc();
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("gc after 2  \t " + myObject);
    }
}
