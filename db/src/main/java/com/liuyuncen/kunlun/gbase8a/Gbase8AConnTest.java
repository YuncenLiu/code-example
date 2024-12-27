package com.liuyuncen.kunlun.gbase8a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.kunlun.gbase8a
 * @author: Xiang想
 * @createTime: 2024-12-27  09:39
 * @description: TODO
 * @version: 1.0
 */
public class Gbase8AConnTest {
    public static void main(String[] args) {
        // GBase JDBC 连接字符串，需要根据实际情况进行修改
        String jdbcUrl = "jdbc:gbase://192.168.58.155:5258/yun";
        String username = "yun";
        String password = "Abcd!234";

        try {
            // 加载GBase JDBC驱动
            Class.forName("com.gbase.jdbc.Driver");

            // 建立连接
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            // 创建Statement对象执行查询
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tbuser");

            // 处理结果集
            while (rs.next()) {
                // 根据你的表结构，获取字段值
                // 例如，假设表有两个字段：id 和 name
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                System.out.println("userid: " + id + ", username: " + name);
            }

            // 关闭资源
            rs.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
