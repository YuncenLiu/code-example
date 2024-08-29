package com.liuyuncen.juc.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述： 公平锁与非公平锁
 *
 * @author: Xiang
 * @date: 2024年08月30日 01:23:37
 * @Description:
 * @return
 */
class Ticket{
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);

    public void sale(){
        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName() + "\t NO." + (number--) + " \t , have: " +number);
            }
        }finally {
            lock.unlock();
        }
    }
}
public class SaleTicketDemo {
    public static void main(String[] args) {

        // 模拟三名售票员卖票
        Ticket ticket = new Ticket();
        new Thread(() ->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(() ->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(() ->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"C").start();
    }
}
