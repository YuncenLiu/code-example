package com.liuyuncen.juc.interrupt;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.interrupt
 * @author: Xiang想
 * @createTime: 2024-11-28  13:34
 * @description: TODO
 * @version: 1.0
 */
public class InterruptDemo4 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("\t --- \t");
        Thread.currentThread().interrupt();
        System.out.println("\t --- \t");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());


    }
}
