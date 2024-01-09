package com.kunlun.sftp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.sftp
 * @author: Xiang想
 * @createTime: 2023-11-23  11:42
 * @description: TODO
 * @version: 1.0
 */
public class LocalCountFile {
    public static String directoryPath = "/Users/xiang/2/";
    public static SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

    public static LocalDate startDate = LocalDate.of(2022, 12, 1);
    public static LocalDate endDate = LocalDate.of(2023, 5, 5);



    public static void main(String[] args) {
        scanFiles(new File(directoryPath));
    }


    public static void scanFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        scanFiles(file); // 递归调用自身处理子目录
                    } else if (file.getName().endsWith(".txt")) {
                        int count = 0;
                        boolean flag = true;
                        String dateStr = file.getName().replace(".txt", "");
                        Date parse = null;
                        try {
                            parse = sf.parse(dateStr);
                        } catch (ParseException e) {
                            System.out.println("因为时间转化问题，不纳入计算中："+file.getAbsolutePath());
                        }

                        //
                        if (parse!=null){
                            Instant instant = parse.toInstant();
                            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                            if (localDate.isAfter(startDate) && localDate.isBefore(endDate)) {
                                try {
                                    count = readJson(file);
                                }catch (Exception e){
                                    flag = false;
                                }
                                String path = file.getAbsolutePath().replace("/Users/xiang/2", "");
                                System.out.println(path +" - " +count + " - " +flag);
                            }
                        }
                    }
                }
            }
        }
    }





    public static int readJson(File file){
        String json = readJsonFile(file);
        JSONObject jsonObject = new JSONObject(json);
        return Integer.parseInt(jsonObject.get("totalNum").toString());
    }


    private static String readJsonFile(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
