package com.kunlun.sftp;

import com.jcraft.jsch.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.sftp
 * @author: Xiang想
 * @createTime: 2023-11-23  10:24
 * @description: TODO
 * @version: 1.0
 */
public class CountFile {

    public static String localFilePath = "/Users/xiang/Desktop/xml/";
   public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   public static SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
   public static DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

    public static LocalDate startDate = LocalDate.of(2022, 12, 1);
    public static LocalDate endDate = LocalDate.of(2023, 5, 5);

    public static void main(String[] args) {
        String host = "10.6.128.108";
        int port = 22;
        String username = "data_sftp";
        String password = "CbJvf9BLx3FJVSMf";
        String remoteDirectory = "/";


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

                    if (filename.contains(".txt")) {

                        String dataStr = filename.replace(".txt", "");
                        try {
                            Date parse = sf.parse(dataStr);
                            Instant instant = parse.toInstant();
                            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                            if (localDate.isAfter(startDate) && localDate.isBefore(endDate)) {
                                channel.get(directory + filename, localFilePath);

                                int count = readJson();
                                System.out.println(directory + filename + "," + count);
                            }
                        }catch (Exception e){
                            System.out.println(directory + filename +" 异常");
                        }
                    }
                }
            }
        }
    }


    public static int readJson(){
        File directory = new File(localFilePath);
        File[] files = directory.listFiles();
        if (files.length == 1) {
            File file = files[0];
            String json = readJsonFile(file);
            JSONObject jsonObject = new JSONObject(json);
            file.delete();
            return Integer.parseInt(jsonObject.get("totalNum").toString());
        }
        throw new RuntimeException();
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
