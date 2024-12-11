package com.liuyuncen.juc.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.aqs
 * @author: Xiang想
 * @createTime: 2024-12-09  11:49
 * @description: TODO
 * @version: 1.0
 */
public class AQSDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);

        lock.lock();
        try {

        }finally {
            lock.unlock();
        }
    }
}
