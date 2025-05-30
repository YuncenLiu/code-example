package com.liuyuncen;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-06-18  15:12
 * @description: TODO
 * @version: 1.0
 */
public class SendMail {
    public static void main(String[] args) {
        String to = "yuncenliu@163.com";
//        String to = "18333609932@163.com";
//        String to = "zhanghaonan@kunlunhealth.com";


        for (String arg : args) {
            if (arg.contains("to")) {
                to = arg.substring(arg.indexOf("=") + 1);
                System.out.println("接收方 : " + to);
            }
        }

        String subject = "测试网络环境 - 测试邮箱";
        String body = "这是一封用Java脚本构建的测试邮件，用于验证邮箱网络环境及配置可行性。";

        sendEmail(to, subject, body);
    }

    public static void sendEmail(String to, String subject, String body) {
        // 设置发件人邮箱地址和密码
        final String username = "data_alarm@kunlunhealth.com";
        final String password = "kkwe2ZngQCA2VC4j";

//        final String username = "array_xiangxiang@163.com";
//        final String password = "MEODMDFOOKFAMPQP";

//        String hosts = "fastsmtphz.qiye.163.com";
        String hosts = "mail.kunlunhealth.com";
        String port = "25";

//        final String username = "data@kunlunhealth.com";
//        final String password = "w4XQbwGz5Tg2tjNB";

        // 设置属性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", hosts);  // kunlun 真实ip
//        props.put("mail.smtp.host", "mail.kunlunhealth.com");
//        props.put("mail.smtp.host", "smtp.163.com");  // 我的 163
        props.put("mail.smtp.port", port);

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sim.format(new Date());
        System.out.println("date = " + format);

        System.out.println("hosts = " + hosts);
        System.out.println("port = " + port);
        System.out.println("from = " + username);
        System.out.println("to = " + to);

        // 创建会话
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // 创建消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // 发送消息
            Transport.send(message);

            System.out.println("邮件已发送成功！");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
