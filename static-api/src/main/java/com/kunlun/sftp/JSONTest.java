package com.kunlun.sftp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;

import java.io.FileReader;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.sftp
 * @author: Xiang想
 * @createTime: 2023-11-27  15:38
 * @description: TODO
 * @version: 1.0
 */
public class JSONTest {
    public static void main(String[] args) {
        // 创建 Gson 对象
        Gson gson = new Gson();

        // 读取 JSON 文件
        JsonObject data = null;
        try {
            data = gson.fromJson(new FileReader("/Users/xiang/Desktop/test.json"), JsonObject.class);
        }catch (Exception e){

        }

        // 获取 COMCODE 值
        JsonArray dataArray = data.getAsJsonArray("data");
        for (int i = 0; i < dataArray.size(); i++) {
            String comcode = dataArray.get(i)
                    .getAsJsonObject()
                    .getAsJsonObject("renewal_ss")
                    .get("CONTNO")
                    .getAsString();
            System.out.println(comcode);
        }
    }
}
