package com.liuyuncen.oracle.blob;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Blob;
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

    public static DataSource getDataSourceA() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@term.klhic.com:9029/fktestdb");
        dataSource.setUsername("mnwtn03y");
        dataSource.setPassword("xJl9cwn0f");
        return dataSource;
    }

    public static DataSource getDataSourceB() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@term.klhic.com:9029/fktestdb");
        dataSource.setUsername("mnwtn03y");
        dataSource.setPassword("xJl9cwn0f");
        return dataSource;
    }


    public static void main(String[] args) {
        JdbcTemplate jdbcTemplatea = new JdbcTemplate(getDataSourceA());
        JdbcTemplate jdbcTemplateb = new JdbcTemplate(getDataSourceA());
        System.out.println("jdbcTemplate = " + jdbcTemplatea);
        String sql = "SELECT ID_,BYTES_ FROM ACT_GE_BYTEARRAY WHERE BYTES_ IS NOT null"; // Your SQL query here

        // Execute query and process the result
        jdbcTemplatea.query(sql, (ResultSet rs) -> {
            try {
                Blob blob = rs.getBlob("BYTES_");
                String id_ = rs.getString("ID_");

                String insertSql = "INSERT INTO ACT_GE_BYTEARRAY_20240624 (ID_,BYTES_) VALUES (?, ?)";
                jdbcTemplateb.update(insertSql, id_, blob);

//                if (blob != null) {
//                    try (InputStream inputStream = blob.getBinaryStream()) {
//                        byte[] bytes = new byte[1024]; // or appropriate buffer size
//                        int length;
//                        StringBuilder sb = new StringBuilder();
//                        while ((length = inputStream.read(bytes)) != -1) {
//                            sb.append(new String(bytes, 0, length));
//                        }
//                        String blobAsString = sb.toString();
//                        System.out.println("id_ = " + id_);
//                        System.out.println("Blob data as string: " + blobAsString);
//
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        // Handle IOException
//                    }
//                } else {
//                    System.out.println("BYTES_ field is null.");
//                    // Handle null case
//                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQLException
            }
        });
    }
}
