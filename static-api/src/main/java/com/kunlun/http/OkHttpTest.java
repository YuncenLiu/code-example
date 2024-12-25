package com.kunlun.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.http
 * @author: Xiang想
 * @createTime: 2024-12-24  09:20
 * @description: TODO
 * @version: 1.0
 */
public class OkHttpTest {
    public static void main(String[] args) {
        String url = "http://10.5.3.176:8082/api/xiang-sftp-password-interface/sftp-password-trajectory/query-password" +
                "?timestamp=2024-12-23 10:25:52&orgCode=dev_s_meixin_sftp&dataTypeCode=SP";

        OkHttpClient client = new OkHttpClient();

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
