package com.liuyuncen;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Object object = new Object();  // 强引用
            object = "213";
            ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
            PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);
// 删除强引用，phantomReference 仍然指向对象
// 在回收时，虚引用的对象会被加入到 referenceQueue 中
            phantomReference.equals("213");
            if (phantomReference.isEnqueued()) {
                System.out.println("对象被加入到引用队列");
            } else {
                System.out.println("对象未加入引用队列");
            }
            System.out.println(object);
        }
    }
}
