package com.liuyuncen.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  15:23
 * @description: TODO
 * @version: 1.0
 */
class MyVar{
    public volatile Boolean isInit = Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyVar, Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class , Boolean.class, "isInit");

    public void init(MyVar myVar){
        if (referenceFieldUpdater.compareAndSet(myVar, Boolean.FALSE , Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + "\t ---- start init, need 2 seconds");
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ---- over init");
        }else{
            System.out.println(Thread.currentThread().getName() + "\t ---- 已经有线程在进行初始化工作....");
        }
    }


}
public class AtomicReferenceFiledUpdaterDemo {

    public static void main(String[] args) {
        MyVar myVar = new MyVar();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                myVar.init(myVar);
            },String.valueOf(i)).start();
        }
    }
}
