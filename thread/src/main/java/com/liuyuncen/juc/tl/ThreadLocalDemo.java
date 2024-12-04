package com.liuyuncen.juc.tl;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.tl
 * @author: Xiang想
 * @createTime: 2024-12-03  16:35
 * @description: TODO
 * @version: 1.0
 */

class House{
    int saleCont = 0;

    /**
     * @description: 需求一、5个销售，集团高层只关心销售总量的准确数字
     * @author: Xiang想
     * @date: 2024/12/3 17:15
     **/
    public synchronized void saleHouse(){
        ++saleCont;
    }


    // 非常繁琐
    /*
    ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    */

    // 建议这样写
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);
    public void saleVolumeByThreadLocal(){
        saleVolume.set(1 + saleVolume.get());
    }

}
public class ThreadLocalDemo {
    public static void main(String[] args) throws Exception{
        House house = new House();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    int size = new Random().nextInt(5) + 1;
                    System.out.println("size = " + size);
                    for (int j = 0; j < size; j++) {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }


                    System.out.println("ThreadLocal中获取卖出数量：\t" + house.saleVolume.get());
                }finally {
                    // 用完了 清空，养成好习惯！
                    house.saleVolume.remove();
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 共计卖出多少套：" + house.saleCont);
    }


}
