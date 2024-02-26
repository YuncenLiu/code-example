package com.liuyuncen;

import java.util.concurrent.atomic.LongAdder;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-01-09  09:36
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }

    public void test(){
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.decrement();
        long l = longAdder.longValue();
        System.out.println("l = " + l);
    }
}
