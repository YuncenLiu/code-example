package com.kunlun.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.http
 * @author: Xiang想
 * @createTime: 2024-12-24  09:20
 * @description: TODO
 * @version: 1.0
 */
public class HttpClientTest {
    public static void main(String[] args) {
        // 构造目标 URL，包含查询参数
        String url = "http://10.5.3.176:8082/api/xiang-sftp-password-interface/sftp-password-trajectory/query-password" +
                "?timestamp=2024-12-23%2010:25:52&orgCode=dev_s_meixin_sftp&dataTypeCode=SP";

        // 创建 HttpClient 实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 HttpGet 请求对象
            HttpGet request = new HttpGet(url);

            // 发送请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 获取响应体
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);

                // 打印响应状态码和响应体
                System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
                System.out.println("Response Body: " + responseBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
