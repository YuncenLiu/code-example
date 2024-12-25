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
public class DecrypZipFile {
    public static void main(String[] args) {
        String zipFilePath = "/Users/xiang/7/dev_s_meixin_sftp/chengbao/2024年08月22日.zip";
        String destinationFolder = "/Users/xiang/7/dev_s_meixin_sftp/chengbao/";
        String password = "1m[9Ba=Y0O<:";

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
