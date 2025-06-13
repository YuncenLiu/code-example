package com.liuyuncen;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-06-18  15:12
 * @description: TODO
 * @version: 1.0
 */
public class MyMail {
    public static void main(String[] args) {
        // 发件人电子邮件地址
        String from = "array_xiangxiang@163.com";
        // 收件人电子邮件地址
        String to = "yuncenliu@163.com";
        // SMTP 服务器配置
        String host = "smtp.163.com";
        String port = "465"; // SSL端口
        String username = "array_xiangxiang@163.com"; // 你的163邮箱账号
        String password = "KTSQjWaTA8L9GqGe"; // 你的邮箱授权码


        String name = "老张";
        String user_id = "I15123";
        String user_phone = "15279151605";
        String currentDate = "2025-10-20";

        String content = "<p>尊敬的用户您好：" + name + "</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp; 我是您的理财经理，我的ID为：" + user_id + "</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp; 如果您有理财方面的想法，请联系我电话：" + user_phone + "</p>"
                + "<p><br></p>"
                + "<p style='text-align: right;'>当前时间：" + currentDate + "</p>"
                + "<p style='text-align: right;'>您的专业理财经理 Mr 刘</p>";


        // 设置系统属性
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // 获取默认的 Session 对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建一个默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // 设置邮件的发件人、收件人、主题和正文
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setContent(content, "text/html; charset=UTF-8");
            message.setSubject("Test Subject");

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
