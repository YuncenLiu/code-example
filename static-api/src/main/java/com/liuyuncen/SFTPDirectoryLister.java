package com.liuyuncen;

import com.jcraft.jsch.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-20  14:36
 * @description: TODO
 * @version: 1.0
 */
public class SFTPDirectoryLister {

    public static void main(String[] args) {
        String host = "10.6.128.108";
        int port = 22;
        String username = "hengying";
        String password = "RxdF71B7";
        String remoteDirectory = "/hengying/";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            listFilesRecursively(channel, remoteDirectory);

            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listFilesRecursively(ChannelSftp channel, String directory) throws SftpException, ParseException {
        Vector<ChannelSftp.LsEntry> entries = channel.ls(directory);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        for (ChannelSftp.LsEntry entry : entries) {
            String filename = entry.getFilename();
            if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..")) {
                SftpATTRS attrs = entry.getAttrs();
                String s = attrs.toString();
                String[] split = s.split(" ");
                Date fileData = null;
                if (split.length >= 9){
                    String usTimeStr = split[4] + " " + split[5] + " " + split[6] + " " + split[7] + " " + split[8] + " " + split[9];
                    fileData = df.parse(usTimeStr);
                }
                String format = sdf.format(fileData);

                if (attrs.isDir()) {
                    String subDirectory = directory + filename + "/";
                    listFilesRecursively(channel, subDirectory);
                } else {
                    System.out.println("TIME:" + format + "File: " + directory + filename);
                }
            }
        }
    }
}
