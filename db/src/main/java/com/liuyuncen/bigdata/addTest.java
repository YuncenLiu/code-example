package com.liuyuncen.bigdata;

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
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-21  16:31
 * @description: TODO
 * @version: 1.0
 */
public class addTest {

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xiang?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public static void main(String[] args) {
        final int batchSize = 2;
        List<IndexTest> testDataList = generateTestData(2);


        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "INSERT INTO index_test (name, dt) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
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
