package com.liuyuncen.juc.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.atomic
 * @author: Xiang想
 * @createTime: 2024-12-03  11:50
 * @description: TODO
 * @version: 1.0
 */
public class LongAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.sum());


        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);

        System.out.println(longAccumulator.get());
    }
}
