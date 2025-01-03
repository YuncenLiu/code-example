package com.kunlun.zip;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.zip
 * @author: Xiang想
 * @createTime: 2024-12-17  16:27
 * @description: TODO
 * @version: 1.0
 */
public class DecryptZipFile {
    public static void main(String[] args) {
        String zipFilePath = "/Users/xiang/Desktop/de-sftp/2024年12月26日.zip";
        String destinationFolder = "/Users/xiang/Desktop/de-sftp/";
        String password = "=smEA#Os0jZ";

        try {
            // 创建 ZipFile 对象并解压文件
            ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray());

            if (zipFile.isEncrypted()) {
                System.out.println("开始解压加密文件...");
                zipFile.extractAll(destinationFolder);
                System.out.println("文件已解压到: " + destinationFolder);
            } else {
                System.out.println("该压缩包没有加密！");
            }
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("解压时发生错误：" + e.getMessage());
        }
    }
}
