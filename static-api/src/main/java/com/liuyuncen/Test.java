package com.liuyuncen;

import java.util.HashSet;
import java.util.Set;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-12  18:08
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("1"));
        System.out.println(set.add("1"));
        System.out.println(set.add("1"));
        System.out.println(set.add("1"));

    }
}
