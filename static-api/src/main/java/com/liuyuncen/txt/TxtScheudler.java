package com.liuyuncen.txt;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.txt
 * @author: Xiang想
 * @createTime: 2024-03-12  13:15
 * @description: TODO
 * @version: 1.0
 */
public class TxtScheudler {
    @SneakyThrows
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/xiang/Desktop/pay.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i%3==0) {
                    list1.add(line);
                }
                if (i%3==1) {
                    list2.add(line);
                }
                if (i%3==2) {
                    list3.add(line);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i)+","+list2.get(i)+","+list3.get(i));
        }

    }
}
