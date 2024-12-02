package com.liuyuncen.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cas
 * @author: Xiang想
 * @createTime: 2024-12-02  13:00
 * @description: TODO
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class Book{
    private int id;
    private String bookName;
}
public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book javaBook = new Book(1, "javaBook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<Book>(javaBook,1);

        System.out.println(stampedReference.getReference().toString() + "\t" + stampedReference.getStamp());


        Book mysqlBook = new Book(2, "mysqlBook");
        System.out.println(stampedReference.compareAndSet(javaBook,mysqlBook,1,2) + "\t" + stampedReference.getReference().toString() + "\t" + stampedReference.getStamp());
        System.out.println(stampedReference.compareAndSet(mysqlBook,javaBook,2,3) + "\t" + stampedReference.getReference().toString() + "\t" + stampedReference.getStamp());
    }

}
