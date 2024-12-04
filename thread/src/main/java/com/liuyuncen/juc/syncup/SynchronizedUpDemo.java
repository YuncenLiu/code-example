package com.liuyuncen.juc.syncup;

import org.openjdk.jol.info.ClassLayout;

public class SynchronizedUpDemo {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println("10进制：\t"+ o.hashCode());
        System.out.println("16进制：\t"+ Integer.toHexString(o.hashCode()));
        System.out.println("2进制：\t"+ Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // 1100111011100110010011110110110
        //01100111011100110010011110110110
    }
}
