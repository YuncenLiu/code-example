package com.liuyuncen;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * book: https://github.com/YuncenLiu/code-example
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-18  13:27
 * @description: TODO
 * @version: 1.0
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception{
        String date1 = "2023-11-25 00:20:23";
        String date2 = "2023-11-25";

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date parse = sim.parse(date1);
        System.out.println("parse = " + parse);
        Date parse1 = sim.parse(date2);
        System.out.println("parse1 = " + parse1);


    }
}
