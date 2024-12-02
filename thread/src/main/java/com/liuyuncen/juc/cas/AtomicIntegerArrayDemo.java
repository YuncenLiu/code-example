package com.liuyuncen.juc.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  14:05
 * @description: TODO
 * @version: 1.0
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});


        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }

        System.out.println();


        System.out.println(atomicIntegerArray.getAndSet(0,1122) + "\t" + atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.getAndIncrement(0) + "\t" + atomicIntegerArray.get(0));


    }
}
