package com.liuyuncen.cglib;

import java.lang.reflect.Method;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-08-04  17:51
 * @description: TODO
 * @version: 1.0
 */
public class ReflectionExample {
    public static void main(String[] args) {
        MyInterface myInterface = new MyClass();
        Class<?> myInterfaceClass = myInterface.getClass();
        for (int i = 1; i < 4; i++) {
            String methodName = "method" + i;
            Method declaredMethod = null;
            try {
                declaredMethod = myInterfaceClass.getDeclaredMethod(methodName);
                Object invoke = declaredMethod.invoke(myInterface);
                System.out.println("invoke = " + invoke);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

}
