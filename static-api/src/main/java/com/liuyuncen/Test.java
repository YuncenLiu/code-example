package com.liuyuncen;

import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-12  18:08
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        String s = new Date().toString();
        System.out.println("s = " + s);
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date parse = df.parse(s);
        System.out.println("parse = " + parse);
    }
}
