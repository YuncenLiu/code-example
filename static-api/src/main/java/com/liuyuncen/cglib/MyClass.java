package com.liuyuncen.cglib;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.cglib
 * @author: Xiang想
 * @createTime: 2023-08-04  17:52
 * @description: TODO
 * @version: 1.0
 */
public class MyClass implements MyInterface{
    @Override
    public String method1() {
        return "Hello 1";
    }

    @Override
    public String method2() {
        return "Hello 2";
    }

    @Override
    public String method3() {
        return "Hello 3";
    }
}
