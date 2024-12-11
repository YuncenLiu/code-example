package com.liuyuncen.juc.syncup;

import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁的 实验过程
 * JVM 参数关闭 偏向锁过程 -XX:-UseBiasedLocking
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.syncup
 * @author: Xiang想
 * @createTime: 2024-12-06  16:35
 * @description: TODO
 * @version: 1.0
 */
public class SynchronizedUpDemo2 {

    public static void main(String[] args) {
        Object o = new Object();
//        new Thread(()->{
//            synchronized (o){
//                System.out.println(o.hashCode());
//
//                String s = ClassLayout.parseInstance(o).toPrintable();
//                System.out.println("s = " + s);
//            }
//        },"t1").start();
//
//        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(ClassLayout.parseInstance(new SynchronizedUpDemo2()).toPrintable());
    }
}
