package com.liuyuncen.bill;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.bill
 * @author: Xiang想
 * @createTime: 2023-10-13  17:32
 * @description: TODO
 * @version: 1.0
 */
public class AnalyseTest {
    public static void main(String[] args) {
        String filePath = "/Users/xiang/Desktop/zfb.txt";

        List<String> lists = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lists.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < lists.size(); i++) {
            if (i%4==3) {
                System.out.println(lists.get(i));
            }
        }
    }
}
