package com.liuyuncen.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
