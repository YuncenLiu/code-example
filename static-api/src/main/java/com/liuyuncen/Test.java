package com.liuyuncen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
        List<Person> personList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int r = random.nextInt(100);
            personList.add(new Person("A-"+r,r));
        }
        for (Person person : personList) {
            System.out.println(person);
        }

        System.out.println("排序前 --------- ");

        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                System.out.println("Integer.compare(p1.getSerial(), p2.getSerial()); -> "  + " p1.getSerial()" + p1.getSerial() + "p2.getSerial()"  + p2.getSerial()  + "= " + Integer.compare(p1.getSerial(), p2.getSerial()));
                return Integer.compare(p1.getSerial(), p2.getSerial());
            }
        });

        for (Person person : personList) {
            System.out.println(person);
        }

        System.out.println("二次排序 --------- ");

        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                System.out.println("Integer.compare(p1.getSerial(), p2.getSerial()); -> "  + " p1.getSerial()" + p1.getSerial() + "p2.getSerial()"  + p2.getSerial()  + "= " + Integer.compare(p1.getSerial(), p2.getSerial()));
                return Integer.compare(p1.getSerial(), p2.getSerial());
            }
        });

    }
}

class Person{
    String id ;
    int serial;
    public Person(String id, int serial) {
        this.id = id;
        this.serial = serial;
    }
    public int getSerial() {
        return serial;
    }
    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                '}';
    }
}
