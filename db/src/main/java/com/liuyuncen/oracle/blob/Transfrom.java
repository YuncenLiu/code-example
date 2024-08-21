package com.liuyuncen.oracle.blob;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.oracle.blob
 * @author: Xiang想
 * @createTime: 2024-06-26  14:41
 * @description: TODO
 * @version: 1.0
 */
public class Transfrom {

    public static DataSource getDataSourcePro() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@term.klhic.com:9044/riskdb");
        dataSource.setUsername("gemaaa");
        dataSource.setPassword("UWVvJyywS");
        return dataSource;
    }

    public static DataSource getDataSourceTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@term.klhic.com:9029/fktestdb");
        dataSource.setUsername("4rmf5bhv");
        dataSource.setPassword("DCK0s8LSc");
        return dataSource;
    }


    public static void main(String[] args) {

    }

    public static void transferByteArray() {
        JdbcTemplate jdbcTemplatePro = new JdbcTemplate(getDataSourcePro());
        JdbcTemplate jdbcTemplateTest = new JdbcTemplate(getDataSourceTest());

        String sqlSelect = "SELECT BYTES_ FROM risk.ACT_GE_BYTEARRAY where ID_ ='2135102' ";
        byte[] bytes = jdbcTemplatePro.queryForObject(sqlSelect, byte[].class);

        if (bytes != null) {
            String sqlInsert = "INSERT INTO FKTEST01.ACT_GE_BYTEARRAY_20240627 (BYTES_) VALUES (?)";
            jdbcTemplateTest.update(sqlInsert, bytes);
            System.out.println("Data transferred successfully.");
        } else {
            System.out.println("No data found in source database.");
        }
    }

    public static void dataSync(){
        JdbcTemplate jdbcTemplatePro = new JdbcTemplate(getDataSourcePro());
        JdbcTemplate jdbcTemplateTest = new JdbcTemplate(getDataSourceTest());
        String sql = "SELECT ID_ FROM risk.ACT_HI_COMMENT"; // Your SQL query here

        // Execute query and process the result
        jdbcTemplatePro.query(sql, (ResultSet rs) -> {
            try {
                String id = rs.getString("ID_");

                String sqlSelect = "SELECT FULL_MSG_ FROM risk.ACT_HI_COMMENT where ID_ ='"+id+"' ";
                byte[] bytes = jdbcTemplatePro.queryForObject(sqlSelect, byte[].class);
                String sqlInsert = "update FKTEST01.ACT_HI_COMMENT set FULL_MSG_ = ? where ID_= ?";
                jdbcTemplateTest.update(sqlInsert, bytes,id);
                System.out.println("Data transferred successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQLException
            }
        });
    }
}
