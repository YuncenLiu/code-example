package com.liuyuncen.juc.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 300; i++) {
                System.out.println("---- " + i);
            }
        },"t1");
        t1.start();


        System.out.println("t1-01 线程默认中断标识：" + t1.isInterrupted());
        try { TimeUnit.MILLISECONDS.sleep(2); } catch (InterruptedException e) {e.printStackTrace();}
        t1.interrupt();

        System.out.println("t1-02 线程调用 interrupt 后中断标识 " + t1.isInterrupted());
        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) {e.printStackTrace();}

        // t1 早就循环完了300次，到这里早已经是一个死掉的线程，所以直接就返回了 false
        System.out.println("t1-03 线程调用 interrupt 后中断标识 " + t1.isInterrupted());


    }
}
