package com.liuyuncen.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  11:43
 * @description: TODO
 * @version: 1.0
 */

@AllArgsConstructor
@Data
@ToString
class User{
    String userName;
    int age;
}
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User z3 = new User("z3", 23);
        User ls = new User("ls", 28);

        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,ls) + "\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,ls) + "\t " + atomicReference.get().toString());
    }
}
