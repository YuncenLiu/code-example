package com.liuyuncen.kunlun.gbase8a;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.kunlun.gbase8a
 * @author: Xiang想
 * @createTime: 2025-01-21  10:51
 * @description: TODO
 * @version: 1.0
 */
public class EastGbaseRunDdl {
    public static DataSource getOrgGBaeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.gbase.jdbc.Driver");
        dataSource.setUrl("jdbc:gbase://10.4.43.53:5258/cc?failoverEnable=true&hostList=10.4.43.57,10.4.43.61&gclusterId=gc1");
        dataSource.setUsername("ods");
        dataSource.setPassword("M23sjx7w$@Eq@JY7");
        return dataSource;
    }


    public static List<String> getSQLFile(){
        Path directory = Paths.get("/Users/xiang/xiang/study/Python/tools/caculator/target/gbase8a_ddl");

        // 用来存储所有 SQL 文件内容的 List
        List<String> sqlContents = new ArrayList<>();

        // 遍历目录及子文件夹
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // 只处理 .sql 文件
                    if (Files.isRegularFile(file) && file.toString().endsWith(".sql")) {
                        try {
                            // 读取 SQL 文件内容并保存到 List 中
                            List<String> fileLines = Files.readAllLines(file, Charset.forName("UTF-8"));
                            // 将文件内容加入到 List
                            sqlContents.add(String.join("\n", fileLines));
                        } catch (IOException e) {
                            System.err.println("Error reading file " + file.toString() + ": " + e.getMessage());
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // 如果文件访问失败，打印错误信息
                    System.err.println("Failed to access file: " + file.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Error occurred while traversing the directory: " + e.getMessage());
        }

        // 输出 SQL 内容的 List
        // for (String sqlContent : sqlContents) {
        //     System.out.println("SQL Content:\n" + sqlContent + "\n");
        // }
        return sqlContents;
    }

    public static void main(String[] args) {
        JdbcTemplate jdbc = new JdbcTemplate(getOrgGBaeDataSource());

        int count = 0;
        int succ = 0;
        List<String> errList = new ArrayList<>();
        for (String sql : getSQLFile()) {
            String[] split = sql.split(";");
            count ++ ;
            try {
                for (String s : split) {
                    jdbc.execute(s);
//                    System.out.println("Executed SQL: " + s);
                }
                succ ++ ;
            } catch (Exception e) {
                errList.add(split[0]);
                System.err.println("Error executing SQL: " + sql + "\n" + e.getMessage());
            }
        }

        System.out.println("总文件：" + count);
        System.out.println("成功：" + succ);
        System.out.println("失败 ===>>>>>>> " );

        for (String err : errList) {
            System.out.println(err);
        }
    }
}
