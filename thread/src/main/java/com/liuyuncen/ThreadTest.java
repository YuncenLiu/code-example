package com.liuyuncen;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-18  13:27
 * @description: TODO
 * @version: 1.0
 */
public class ThreadTest {
    public static void main(String[] args) {
        IntervalThread intervalThread = new IntervalThread(5); // 每隔5秒执行一次任务
        Thread thread = new Thread(intervalThread);
        thread.start();

        // 在需要的时候关闭线程
        try {
            Thread.sleep(45000); // 等待15秒
            intervalThread.stop(); // 关闭线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
