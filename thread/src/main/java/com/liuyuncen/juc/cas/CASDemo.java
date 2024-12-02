package com.liuyuncen.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.csa
 * @author: Xiang想
 * @createTime: 2024-11-29  16:06
 * @description: TODO
 * @version: 1.0
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2023) + "\t" + atomicInteger.get());
        atomicInteger.compareAndSet(2022,5);
        System.out.println(atomicInteger.compareAndSet(5,2023) + "\t" + atomicInteger.get());

    }
}
