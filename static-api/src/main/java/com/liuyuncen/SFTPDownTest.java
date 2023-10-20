package com.liuyuncen;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-20  16:13
 * @description: TODO
 * @version: 1.0
 */
public class SFTPDownTest {
    public static void main(String[] args) {
        String host = "10.6.128.108";
        int port = 22;
        String username = "hengying";
        String password = "RxdF71B7";
        String remoteFilePath = "/hengying/xml/deliverreport/2023/20231019.xml";
        String localFilePath = "/Users/xiang/Desktop/xml/";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            // 从SFTP服务器下载文件到本地
            channel.get(remoteFilePath, localFilePath);

            channel.disconnect();
            session.disconnect();

            System.out.println("文件下载完成。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
