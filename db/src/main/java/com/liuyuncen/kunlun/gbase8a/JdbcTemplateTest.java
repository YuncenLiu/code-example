package com.liuyuncen.kunlun.gbase8a;

import com.liuyuncen.bigdata.IndexTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.kunlun.gbase8a
 * @author: Xiang想
 * @createTime: 2024-12-27  11:04
 * @description: TODO
 * @version: 1.0
 */
public class JdbcTemplateTest {

    public static DataSource getGBaeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.gbase.jdbc.Driver");
        dataSource.setUrl("jdbc:gbase://192.168.58.155:5258/yun");
        dataSource.setUsername("yun");
        dataSource.setPassword("Abcd!234");
        return dataSource;
    }

    public static DataSource getOrgGBaeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.gbase.jdbc.Driver");
        dataSource.setUrl("jdbc:gbase://10.4.43.53:5258/eastusertest?failoverEnable=true&hostList=10.4.43.57,10.4.43.61&gclusterId=gc1");
        dataSource.setUsername("eastusertest");
        dataSource.setPassword("uoz@a`h#WNk4g^&M");
        return dataSource;
    }

    public static DataSource getMySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xiang?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("----- 第 " + i + " 次测试");
            testGbaseToMysql();
        }
    }

    public static void testGbaseToMysql(){
        final int batchSize = 500;
        List<IndexTest> testDataList = generateTestData(500);
        JdbcTemplate gbaseJdbcTemplate = new JdbcTemplate(getOrgGBaeDataSource());
        JdbcTemplate mysqlJdbcTemplate = new JdbcTemplate(getMySQLDataSource());
        String sql = "INSERT INTO index_test (name, dt) VALUES (?, ?)";

        long startGbaseTime = System.currentTimeMillis();
        gbaseJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                IndexTest indexTest = testDataList.get(i);
                ps.setString(1, indexTest.getName());
                ps.setDate(2, new java.sql.Date(indexTest.getDt().getTime()));
            }
            @Override
            public int getBatchSize() {
                return batchSize;
            }
        });
        long endGbaseTime = System.currentTimeMillis();
        System.out.println("Gbase 写入 500条数据 执行时间：" + (endGbaseTime -startGbaseTime) + "ms");

        long startMysqlTime = System.currentTimeMillis();
//        mysqlJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                IndexTest indexTest = testDataList.get(i);
//                ps.setString(1, indexTest.getName());
//                ps.setDate(2, new java.sql.Date(indexTest.getDt().getTime()));
//            }
//            @Override
//            public int getBatchSize() {
//                return batchSize;
//            }
//        });
        long endMysqlTime = System.currentTimeMillis();
        System.out.println("MySQL 写入 500条数据 执行时间：" + (endMysqlTime -startMysqlTime) + "ms");
    }


    private static List<IndexTest> generateTestData(int count) {
        List<IndexTest> testDataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            IndexTest testData = new IndexTest();
            testData.setName("TestName" + i);
            testData.setDt(randomDateWithinAYear());
            testDataList.add(testData);
        }
        return testDataList;
    }

    private static Date randomDateWithinAYear() {
        Random random = new Random();
        long currentTime = System.currentTimeMillis();
        long oneYearAgo = currentTime - 365L * 24 * 60 * 60 * 1000; // 365 days
        return new Date(oneYearAgo + random.nextLong() % (365L * 24 * 60 * 60 * 1000));
    }


}
