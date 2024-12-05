package com.liuyuncen.juc.objectHead;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLDemo {
    public static void main(String[] args) {
        // VM 虚拟机详细细节
//        System.out.println(VM.current().details());
//        System.out.println(VM.current().objectAlignment());

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

class Customer{
    int id;
    boolean flag = false;
    String name = "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good" +
            "Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good Hello Nice Good";
}
