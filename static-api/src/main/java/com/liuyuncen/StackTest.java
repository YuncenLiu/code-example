package com.liuyuncen;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-26  17:07
 * @description: TODO
 * @version: 1.0
 */
public class StackTest {
    static long count = 0;

    public static void main(String[] args) {
        count++;
        System.out.println("count = " + count);
        main(args);
    }
}
