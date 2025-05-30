package com.kunlun.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.http
 * @author: Xiang想
 * @createTime: 2024-12-24  09:20
 * @description: TODO
 * @version: 1.0
 */
public class OkHttpTestSleep {
    public static void main(String[] args) {
        String url = "http://localhost:8082/sleep";

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)    // 连接超时
                .writeTimeout(300, TimeUnit.SECONDS)      // 写超时
                .readTimeout(300, TimeUnit.SECONDS)       // 读超时
                .build();

        // 创建请求对象
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // 输出响应状态码和响应体
            System.out.println("Response Code: " + response.code());
            System.out.println("Response Body: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
