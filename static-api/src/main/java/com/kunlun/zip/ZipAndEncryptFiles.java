package com.kunlun.zip;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;

/**
 * 加密压缩zip
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.zip
 * @author: Xiang想
 * @createTime: 2024-12-17  16:28
 * @description: TODO
 * @version: 1.0
 */
public class ZipAndEncryptFiles {
    public static void main(String[] args) {
        String[] filesToCompress = {
            "/Users/xiang/Desktop/sftp/2024年12月26日.txt"
        };
        // 输出压缩文件的路径
        String zipFilePath = "/Users/xiang/Desktop/de-sftp/2024年12月26日.zip";
        // 设置压缩包的密码
        String password = "=smEA#Os0jZ";

        try {
            // 创建 ZipFile 对象，指定压缩包输出路径
            ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray());
            // 设置压缩参数
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(CompressionMethod.DEFLATE);  // 使用标准的压缩方法
            parameters.setCompressionLevel(CompressionLevel.NORMAL);  // 设置压缩级别
            parameters.setEncryptFiles(true);  // 启用文件加密
            parameters.setEncryptionMethod(EncryptionMethod.AES);  // 使用 ZIP 标准加密方法
            // 将文件添加到压缩包中
            for (String filePath : filesToCompress) {
                File file = new File(filePath);
                if (file.exists()) {
                    zipFile.addFile(file, parameters);
                    System.out.println("成功添加文件: " + filePath);
                } else {
                    System.out.println("文件不存在: " + filePath);
                }
            }
            System.out.println("文件已加密压缩并保存到: " + zipFilePath);
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("压缩文件时发生错误：" + e.getMessage());
        }
    }
}
