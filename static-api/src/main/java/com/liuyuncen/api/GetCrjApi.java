package com.liuyuncen.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.api
 * @author: Xiang想
 * @createTime: 2024-02-23  17:17
 * @description: TODO
 * @version: 1.0
 */
public class GetCrjApi {
    public static void main(String[] args) {
        try {
            String urlStr = "https://zwfw.gaj.beijing.gov.cn/crjfjj/api/getBookXzqh?sldttype=3,5,6&t=1708679218490";
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Sending GET request to URL: " + urlStr);
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString());
            // 将JSON字符串转换为JSONArray
            JSONArray jsonArray = new JSONArray(response.toString());

            // 遍历JSONArray中的每个JSONObject
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String code = obj.getString("code");
                String node = obj.getString("node");
                System.out.println("Code: " + code + ", Node: " + node);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
