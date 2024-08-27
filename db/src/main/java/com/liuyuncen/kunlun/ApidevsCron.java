package com.liuyuncen.kunlun;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.kunlun
 * @author: Xiang想
 * @createTime: 2024-08-27  10:21
 * @description: TODO
 * @version: 1.0
 */
public class ApidevsCron {


    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://term-zz01.klhic.com:9032/apidevs?useSSL=false");
        dataSource.setUsername("umfc897");
        dataSource.setPassword("RbeT6MCrEXvWmEUebISQm0P59VXpHGl1");
        return dataSource;
    }

    public static void main(String[] args) {
        List<String> list = setCron();


        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 60; i++) {
            int i1 = i % 24;
            for (int j = 0; j < 24; j++) {
                String hour = String.format("%02d", j);
                String min = String.format("%02d", i);
                String id = list.get(i1);
                StringJoiner sj = new StringJoiner(";");
                // System.out.println(list.get(i1) + "----" + i1 + "-----" +hour+":"+ min);
                String s = map.get(id);
                if (null != s){
                    sj.add(s);
                    sj.add(hour+":"+min);
                    map.put(id,sj.toString());
                }else{
                    sj.add(hour+":"+min);
                    map.put(id,sj.toString());
                }
            }
        }

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        final int batchSize = 2;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " ---- " + entry.getValue());
            String updateSql = "update screenshot set cron = ? where id = ? ";

            jdbcTemplate.batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, entry.getValue());
                    ps.setString(2, entry.getKey());
                }
                @Override
                public int getBatchSize() {
                    return batchSize;
                }
            });
        }






    }

    public static List<String> setCron() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "select id from screenshot";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        List<String> ids = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            ids.add(String.valueOf(map.get("id")));
        }
        return ids;
    }

}
