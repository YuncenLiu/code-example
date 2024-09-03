package com.liuyuncen.juc.locks;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.locks
 * @author: Xiang想
 * @createTime: 2024-09-03  11:47
 * @description: TODO
 * @version: 1.0
 */
public class MyTicketDemo {
    public static void main(String[] args) {


        new Thread(() ->{
            Ticket ticket = new Ticket();


        }).start();
    }
}
