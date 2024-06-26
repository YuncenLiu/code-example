package com.kunlun.risk;

import java.io.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.risk
 * @author: Xiang想
 * @createTime: 2024-06-26  14:17
 * @description: TODO
 * @version: 1.0
 */
public class MarginSql {
    public static void main(String[] args) {
        // 指定源目录和目标文件
        String sourceDir = "/Users/xiang/Desktop/risk/";
        String targetFile = "/Users/xiang/Desktop/all.sql";

        // 创建目标文件
        File mergedFile = new File(targetFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFile))) {
            // 获取源目录下的所有文件
            File[] sqlFiles = new File(sourceDir).listFiles((dir, name) -> name.toLowerCase().endsWith(".sql"));

            if (sqlFiles != null) {
                for (File file : sqlFiles) {
                    // 读取每个 SQL 文件并写入到目标文件
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.newLine();
                        }
                        writer.write("\n-- End of file: " + file.getName() + "\n\n");
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + file.getName());
                        e.printStackTrace();
                    }
                }
                System.out.println("All SQL files merged successfully into " + targetFile);
            } else {
                System.out.println("No SQL files found in the directory: " + sourceDir);
            }

        } catch (IOException e) {
            System.err.println("Error writing to file: " + targetFile);
            e.printStackTrace();
        }
    }
}
