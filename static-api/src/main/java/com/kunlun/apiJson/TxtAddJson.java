package com.kunlun.apiJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.apiJson
 * @author: Xiang想
 * @createTime: 2024-06-13  10:17
 * @description: TODO
 * @version: 1.0
 */
public class TxtAddJson {
    public static void main(String[] args) {
        // 指定 JSON 文件的路径
        String filePath = "/Users/xiang/Desktop/api/data_set_config_202406131041.json";

        try {
            // 读取文件内容到字符串
            String content = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");

            // 将字符串解析为 JSONObject
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray root = jsonObject.getJSONArray("root");

            for (int i = 0; i < root.size(); i++) {
                JSONObject item = root.getJSONObject(i);
                String config = item.getString("table_config");
                String id = item.getString("id");

                config = config.replace("\r", "").replace("\n", "");
                config = config.substring(1, config.length() - 1);


                JSONObject fa = JSON.parseObject(config);

                JSONArray columnConfig = fa.getJSONArray("columnConfig");
                String tableName = fa.getString("tableName");

                StringJoiner sj = new StringJoiner(",");
                for (int i1 = 0; i1 < columnConfig.size(); i1++) {
                    JSONObject field = columnConfig.getJSONObject(i1);
                    String tabCol = field.getString("name");
                    sj.add(tabCol);
                }
                String sql = "select " + sj.toString() + " from " + tableName;


                JSONObject addCol = addFile(tableName);
                columnConfig.add(addCol);

//                System.out.println(columnConfig);
//                System.out.println(id + "\t" + config);

                fa.fluentPut("columnConfig",columnConfig);
                String col = "["+fa+"]";


                System.out.println(id + "\t" + sql);
//                System.out.println(id + "\t" + col);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static JSONObject addFile(String tableName) {

        String jsonString = "{\n" +
                "  \"queryField\": true,\n" +
                "  \"name\": \"BUSINESSTYPE\",\n" +
                "  \"typeName\": \"VARCHAR2\",\n" +
                "  \"queryFilter\": \"=\",\n" +
                "  \"asField\": \"BUSINESSTYPE\",\n" +
                "  \"returnField\": true,\n" +
                "  \"tableName\": \"" + tableName + "\"\n" +
                "}";

        // 使用fastjson将JSON字符串转换为JSONObject对象
        return JSONObject.parseObject(jsonString);
    }
}
