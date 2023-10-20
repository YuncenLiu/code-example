package com.liuyuncen;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FTPDirectoryLister {

    public static void main(String[] args) {
        String server = "10.7.128.85";
        int port = 21;
        String username = "hengying";
        String password = "48UaNvGKGTUeDy82";
        String remoteDir = "/hengying/xml/"; // 要遍历的FTP目录

        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // 或者使用FTP.ASCII_FILE_TYPE

            listDirectory(ftpClient, remoteDir);

            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listDirectory(FTPClient ftpClient, String remoteDir) throws IOException {
        FTPFile[] files = ftpClient.listFiles(remoteDir);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (files != null) {
            for (FTPFile file : files) {
                if (file.isDirectory()) {

//                    System.out.println("Directory: " + remoteDir + file.getName());

                    listDirectory(ftpClient, remoteDir + file.getName() + "/");
                } else {
                    Date lastModified = file.getTimestamp().getTime();
                    String formattedDate = sdf.format(lastModified);
                    System.out.println("TIME:" + formattedDate + "File: " + remoteDir + file.getName());


                }
            }
        }
    }
}
