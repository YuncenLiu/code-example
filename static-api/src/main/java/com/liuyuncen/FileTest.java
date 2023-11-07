package com.liuyuncen;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-11-07  13:43
 * @description: TODO
 * @version: 1.0
 */
public class FileTest {

    private final static String txtPath = "/Users/xiang/Desktop/tmp/";

    private final static SimpleDateFormat SIM_MONTH = new SimpleDateFormat("yyyyMM");
    private final static SimpleDateFormat SIM_DAY = new SimpleDateFormat("dd");


    public static String readTxt(String id,Date createDate){
        String basePath = txtPath + SIM_MONTH.format(createDate) + "/" + SIM_DAY.format(createDate) + "/";
        String filePath = basePath + id + ".txt";

        StringBuffer stringBuffer = new StringBuffer();
        try {
            // 创建文件输入流
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // 逐行读取并输出文件内容
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            // 关闭文件流
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuffer);
        return stringBuffer.toString();
    }

    public static boolean writeTxt(String id, Date createDate, String context) {
        boolean flag = false;
        String basePath = txtPath + SIM_MONTH.format(createDate) + "/" + SIM_DAY.format(createDate) + "/";
        String filePath = basePath + id + ".txt";
        File folder = new File(basePath);

        if (!folder.exists()) {
            folder.mkdirs(); // 创建目录及其父目录（如果不存在）
        }

        try {
            // true 追加 、 false 覆盖
            FileWriter writer = new FileWriter(filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(context);
            bufferedWriter.close();
            writer.close();
            System.out.println("数据已写入文件: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = true;
        return flag;
    }

    public static void main(String[] args) {
        String id = "1251251231251";
        String context ="sadgasdfasdgf \n asdgasdgf \n asdf asdg asdg asdg asdg asdg ";
        writeTxt(id,new Date(),context);
        readTxt(id,new Date());
    }

}
