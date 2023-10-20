package com.liuyuncen;

import com.liuyuncen.tool.ApacheFtpClient;

import java.io.IOException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-09-15  10:46
 * @description: TODO
 * @version: 1.0
 */
public class FTPDownloadTest {

    public static void main(String[] args) {
        String server = "10.7.128.85"; // FTP服务器地址
        int port = 21; // FTP服务器端口号
        String username = "hengying"; // FTP用户名
        String password = "48UaNvGKGTUeDy82"; // FTP密码
        String remoteFilePath = "/hengying/xml/deliverreport/2023/"; // 远程文件路径
        String localDirectory = "/Users/xiang/Desktop";
        String localFilePath = "20230911.xml"; // 本地文件路径

        try {
            ApacheFtpClient apacheFtpClient = new ApacheFtpClient(server, port, username, password);
            apacheFtpClient.downloadFile(remoteFilePath,localDirectory,localFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
