package com.liuyuncen.east;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.east
 * @author: Xiang想
 * @createTime: 2025-03-06  15:44
 * @description: TODO
 * @version: 1.0
 */
public class CompareDbToTxt {

    public static DataSource getDataSourcePro() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@term-zz03.klhic.com:9006/eastpre");
        dataSource.setUsername("lx2r");
        dataSource.setPassword("g3qY2MePbHIPk");
        return dataSource;
    }


    public static List<String> otherColumn() {
        List<String> list = new ArrayList<>();
        list.add("SJBSPCH");
        list.add("ORG_CODE");
        list.add("DEPT_CODE");
        list.add("DATAID");
        list.add("SRC_ORG");
        list.add("INTACT_FLAG");
        list.add("SPLIT_DATE");
        list.add("XQDM");
        list.add("ORG_CODE2");
        list.add("LSHD");
        list.add("ORG_CODE3");
        list.add("DATA_TYPE");
        list.add("CREATE_DT");
        list.add("CREATE_USER");
        list.add("MODIFY_USER");
        list.add("MODIFY_DT");
        list.add("SPLIT_ORG");
        list.add("DATA_DATE");
        list.add("SOURCE_TYPE");
        list.add("OA_ID");
        list.add("ZTSJX");
        return list;
    }

    public static void readAndPrintFiles() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourcePro());
        List<String> otherLists = otherColumn();

        File directory = new File("/Users/xiang/Desktop/000097310000-20250131");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".txt")) {

                        List<List<String>> hexTable = new ArrayList<>();
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                // 按列分隔符 0xA1 分割每行内容
                                String[] hexParts = line.split("\u0001");
                                List<String> row = new ArrayList<>();
                                for (String hex : hexParts) {
                                    if (!hex.isEmpty()) {
                                        row.add(hex);
                                    }else{
                                        row.add("null");
                                    }
                                }
                                hexTable.add(row);
                            }
                        } catch (IOException e) {
                            System.out.println("读取文件时出错: " + e.getMessage());
                        }


                        String name = file.getName();
                        System.out.println("文件名: " + name);
                        String[] split = name.split("-");
                        if (split.length > 2) {
                            String tableName = split[1];
                            String sqlSelect = "SELECT * FROM etl." + tableName + " where sjbspch = '20250131' and org_code = '000097310000'";

                            System.out.println(sqlSelect);

                            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sqlSelect);


                            for (int i = 0; i < maps.size(); i++) {



                                List<String> list = new ArrayList<>();
                                Map<String, Object> map = maps.get(i);
                                String lsh = String.valueOf(map.get("LSH"));

                                for (int i1 = 0; i1 < hexTable.size(); i1++) {
                                    List<String> list1 = hexTable.get(i1);
                                    String tabLsh = list1.get(0);
                                    if (tabLsh.equals(lsh)) {
                                        list = list1;
                                        break;
                                    }

                                }

                                Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                                int listIndex = 0;

                                while (iterator.hasNext()) {
                                    Map.Entry<String, Object> entry = iterator.next();

                                    String key = entry.getKey();
                                    if (otherLists.contains(key)) {
                                        break;
                                    }

                                    String txtValue = "";
                                    try {
                                        txtValue = list.get(listIndex);
                                    }catch (Exception e){
                                        System.err.println("表为：【"+tableName+"】存在异常");
                                        break;
                                    }
                                    listIndex ++;

                                    Object object = entry.getValue();
                                    String tableValue = "";
                                    if (object!=null ){
                                        tableValue = String.valueOf(object);
                                    }else {
                                        tableValue = "null";
                                    }

                                    if (!tableValue.equals(txtValue)){


                                        try {
                                            if (txtValue.length() > 0 && tableValue.length() >0) {
                                                if (Double.parseDouble(tableValue) != Double.parseDouble(txtValue)) {
                                                    System.err.println("表为：【"+tableName+"】LSH为:【"+lsh+"】数据库内容:【" + tableValue + "】,txt文本内容:【"+ txtValue+"】");
                                                }
                                            }else{

                                                System.err.println("表为：【"+tableName+"】LSH为:【"+lsh+"】数据库内容:【" + tableValue + "】,txt文本内容:【"+ txtValue+"】");
                                            }
                                        }catch (Exception e){
                                            System.err.println("表为：【"+tableName+"】LSH为:【"+lsh+"】数据库内容:【" + tableValue + "】,txt文本内容:【"+ txtValue+"】");
                                        }


                                    } else {
                                        if (Math.random() < 0.01) { // 1/100的概率执行
                                            System.out.println("表为：【"+tableName+"】LSH为:【"+lsh+"】数据库内容:【" + tableValue + "】,txt文本内容:【"+ txtValue+"】");
                                        }
                                    }

                                }
                            }
                        }
                    }

                }
            } else {
                System.out.println("目录为空");
            }
        } else {
            System.out.println("目录不存在或不是一个有效目录");
        }

    }

    public static void main(String[] args) {
//        JdbcTemplate jdbcTemplateTest = new JdbcTemplate(getDataSourcePro());
//        String sqlSelect = "SELECT * FROM lpajxxb";
//
//        List<Map<String, Object>> maps = jdbcTemplateTest.queryForList(sqlSelect);
//        List<String> otherLists = otherColumn();
//
//        for (Map<String, Object> map : maps) {
//            map.forEach((k,v) ->{
//                if (otherLists.contains(k)) {
//                    return;
//                }
//                System.out.println(k + "->" + v);
//            });
//        }
        readAndPrintFiles();
    }
}
