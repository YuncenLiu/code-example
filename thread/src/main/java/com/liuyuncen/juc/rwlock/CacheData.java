package com.liuyuncen.juc.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 利用锁降级，实现业务
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.rwlock
 * @author: Xiang想
 * @createTime: 2024-12-11  14:42
 * @description: TODO
 * @version: 1.0
 */
public class CacheData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    void processCachedData(){
        rwLock.readLock().lock();
        if (!cacheValid){
            // 必须释放读锁，才能允许写锁
            rwLock.readLock().unlock();
            rwLock.writeLock().lock();
            try{
                if(!cacheValid){
                    // 业务逻辑
                    cacheValid = true;
                }
                rwLock.readLock().lock();
            }finally {
                // 释放写锁，允许读
                rwLock.writeLock().unlock();
            }
            try {
                // 业务逻辑
            }finally {
                rwLock.readLock().unlock();
            }
        }
    }
}
