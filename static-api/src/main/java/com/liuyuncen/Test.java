package com.liuyuncen;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-12  18:08
 * @description: TODO
 * @version: 1.0
 */
public class Test {

    public static Set<Long> sets = new HashSet<>();
    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Test test = new Test();
            test.test();
        }


    }

    @SneakyThrows
    public void test(){
        Test test = new Test();

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sets.add(test.getUid());
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println("sets = " + sets.size());
    }
    public synchronized long getUid(){
        long timestampMillis = System.currentTimeMillis();
        Date data = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy");
        String uuid = String.valueOf(UUID.randomUUID().getMostSignificantBits());
        return Long.parseLong(sim.format(data) + String.valueOf(timestampMillis).substring(4)+uuid.substring(5,11));
    }
}
