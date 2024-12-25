package com.kunlun.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.URLEncoder.encode;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.http
 * @author: Xiang想
 * @createTime: 2024-12-23  16:20
 * @description: TODO
 * @version: 1.0
 */
public class HttpGetRequestTest {
    public static void main(String[] args) {
        // 2024-12-23 10:25:52
        // 2024-12-23+10%3A25%3A52
        HttpConnect("2024-12-23 10:25:52","dev_huize_sftp","06");
    }

    public static void HttpConnect(String timestamp,String orgCode,String dataTypeCode){
        try {

            if (timestamp == null){
                return;
            }
//            // 对 timestamp 进行 URL 编码，确保空格被转义
            timestamp= encode(timestamp, "utf-8");
//
            timestamp = encode(timestamp, "utf-8");

            // 构建完整的 URL
            String urlString = "http://10.5.3.176:8082/api/xiang-sftp-password-interface/sftp-password-trajectory/query-password-decode" +
                    "?timestamp=" + timestamp +
                    "&orgCode=" + orgCode +
                    "&dataTypeCode=" + dataTypeCode;

            System.out.println(urlString);

            // 创建 URL 对象
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 设置请求头（如需要）
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("User-Agent", "Java HttpURLConnection");

            // 获取响应状态码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 读取响应内容
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                // 使用 InputStream 来读取响应数据
                InputStream inputStream = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // 读取完整的响应内容
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 输出响应内容
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

            // 关闭连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void postToGet(){
        try {
            // 定义请求的 URL
            String urlString = "http://10.5.3.176:8082/api/xiang-sftp-password-interface/sftp-password-trajectory/query-password";
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json");

            // 设置连接超时和读取超时
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // 启用输入输出流
            connection.setDoOutput(true);  // 允许输出流，即发送请求体

            // 创建 JSON 请求体
            String jsonPayload = "{\n" +
                    "    \"timestamp\": \"2024-12-23 10:25:52\",\n" +
                    "    \"orgCode\": \"dev_baotong_sftp\",\n" +
                    "    \"dataTypeCode\": \"10\"\n" +
                    "}";

            // 发送请求体（尽管通常 GET 请求不会有请求体，有些服务可能支持这种做法）
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);  // 发送请求体
            }

            // 获取响应代码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 获取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 打印响应体
            System.out.println("Response Body: " + response.toString());

            // 关闭连接
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
