package com.liuyuncen;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-12-05  11:06
 * @description: TODO
 * @version: 1.0
 */
public class Test3 {

    public static void main(String[] args) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sim.format(new Date()));
    }
}
