package com.liuyuncen.juc.objectHead;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.objectHead
 * @author: Xiang想
 * @createTime: 2024-12-04  15:30
 * @description: TODO
 * @version: 1.0
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.hashCode());


        synchronized (o){

        }

        System.gc();
    }
}
