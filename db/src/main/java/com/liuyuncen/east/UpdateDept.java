package com.liuyuncen.east;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.east
 * @author: Xiang想
 * @createTime: 2025-03-06  18:57
 * @description: TODO
 * @version: 1.0
 */
public class UpdateDept {



    public static DataSource getDataSourcePro() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@term-zz03.klhic.com:9006/eastpre");
        dataSource.setUsername("6f3o");
        dataSource.setPassword("EPSXql0iRJ2Yh");
        return dataSource;
    }


    public static void updateDb(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourcePro());
        MultiValuedMap<String, String> map = getMap();

        for (String key : map.keySet()) {

            Collection<String> deptList = map.get(key);
            List<String> list = new ArrayList<>(deptList);



            String querySql = "select * from " + key ;
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(querySql);

            int index = 0;
            List<Object[] > batchArg = new ArrayList<>();
            for (Map<String, Object> stringObjectMap : maps) {
                String lsh = String.valueOf(stringObjectMap.get("LSH"));
                String deptCode = "";
                if (!list.isEmpty()) {
                    deptCode = list.get(index % list.size());
                    // 这里可以添加你的处理逻辑
                    index++;
                }
//                System.out.println(lsh + " --- " + deptCode);
                String updateSql = "update " + key + " set dept_code ='"+deptCode+"' where lsh = '"+lsh+"';";
                System.out.println(updateSql);
//                jdbcTemplate.batchUpdate(updateSql);
//                String[] array = {key, deptCode, lsh};
//                batchArg.add(array);
            }
//
//            String batchUpdate = "update ? set dept_code ='?' where lsh = '?'";
//            jdbcTemplate.batchUpdate(batchUpdate, batchArg);

        }


    }


    public static MultiValuedMap<String, String > getMap(){
        MultiValuedMap<String, String > map = new ArrayListValuedHashMap<>();
        map.put("D00107_YGXXB","Z01");
        map.put("D00107_YGXXB","Z11");
        map.put("D00108_YGGWXXB","Z01");
        map.put("D00108_YGGWXXB","Z11");
        map.put("D00110_YGCFXXB","Z01");
        map.put("D00110_YGCFXXB","Z11");
        map.put("D00111_YGWZXXB","Z01");
        map.put("D00111_YGWZXXB","Z11");
        map.put("D00112_DJGLZXXB","Z01");
        map.put("D00112_DJGLZXXB","Z11");
        map.put("D00113_DJGXCXXB","Z01");
        map.put("D00113_DJGXCXXB","Z21");
        map.put("D00207_ZHXXB","Z07");
        map.put("D00207_ZHXXB","Z15");
        map.put("D00208_ZJLSB","Z07");
        map.put("D00208_ZJLSB","Z15");
        map.put("D00210_YCXXGLB","D03");
        map.put("D00210_YCXXGLB","D05");
        map.put("D00210_YCXXGLB","D09");
        map.put("D00210_YCXXGLB","D12");
        map.put("D00210_YCXXGLB","D16");
        map.put("D00210_YCXXGLB","D17");
        map.put("D00210_YCXXGLB","Z03");
        map.put("D00401_XSRYXXB","D01");
        map.put("D00401_XSRYXXB","D02");
        map.put("D00401_XSRYXXB","D13");
        map.put("D00401_XSRYXXB","D15");
        map.put("D00402_XSRYYJXXB","D01");
        map.put("D00402_XSRYYJXXB","D02");
        map.put("D00402_XSRYYJXXB","D13");
        map.put("D00402_XSRYYJXXB","D15");
        map.put("D00403_XSRYCFXXB","D01");
        map.put("D00403_XSRYCFXXB","D02");
        map.put("D00403_XSRYCFXXB","D13");
        map.put("D00403_XSRYCFXXB","D15");
        map.put("D00404_XSRYWZXXB","D01");
        map.put("D00404_XSRYWZXXB","D02");
        map.put("D00404_XSRYWZXXB","D13");
        map.put("D00404_XSRYWZXXB","D15");
        map.put("D00405_ZJJGXXB","D02");
        map.put("D00405_ZJJGXXB","D13");
        map.put("D00405_ZJJGXXB","D15");
        map.put("D00406_ZJJGSXFXXB","D02");
        map.put("D00406_ZJJGSXFXXB","D13");
        map.put("D00406_ZJJGSXFXXB","D15");
        map.put("D00406_ZJJGSXFXXB","Z14");
        map.put("D00705_GRBDB","D03");
        map.put("D00705_GRBDB","D05");
        map.put("D00706_GRBDKZXXB","D03");
        map.put("D00706_GRBDKZXXB","D05");
        map.put("D00707_GRXZB","D03");
        map.put("D00707_GRXZB","D05");
        map.put("D00709_KHBDDZB","D03");
        map.put("D00709_KHBDDZB","D05");
        map.put("D00710_BBXRB","D03");
        map.put("D00710_BBXRB","D05");
        map.put("D00711_BDXSRYGLB","D03");
        map.put("D00711_BDXSRYGLB","D05");
        map.put("D00712_XSRYZJYJXXB","D01");
        map.put("D00712_XSRYZJYJXXB","D02");
        map.put("D00712_XSRYZJYJXXB","D15");
        map.put("D00713_ZJJGBDSXFXXB","D02");
        map.put("D00713_ZJJGBDSXFXXB","D13");
        map.put("D00713_ZJJGBDSXFXXB","D15");
        map.put("D00713_ZJJGBDSXFXXB","Z14");
        map.put("D00801_BQXMMXB","D16");
        map.put("D00801_BQXMMXB","D17");
        map.put("D00802_BDJKXXB","D16");
        map.put("D00802_BDJKXXB","D17");
        map.put("D00803_BDHKXXB","D16");
        map.put("D00803_BDHKXXB","D17");
        map.put("D00804_BXZHXXB","D16");
        map.put("D00804_BXZHXXB","D17");
        map.put("D00805_BXZHLSB","D16");
        map.put("D00805_BXZHLSB","D17");
        map.put("D00807_SCJPFB","D16");
        map.put("D00807_SCJPFB","D17");
        map.put("D01103_ZBZDXXB","Z03");
        map.put("D01103_ZBZDXXB","Z03");
        return map;
    }

    public static void main(String[] args) {
        updateDb();
    }
}
