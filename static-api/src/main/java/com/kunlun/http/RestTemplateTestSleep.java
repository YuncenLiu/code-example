package com.kunlun.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.http
 * @author: Xiang想
 * @createTime: 2024-12-24  09:14
 * @description: TODO
 * @version: 1.0
 */
public class RestTemplateTestSleep {
    public static void main(String[] args) {
        try {

            // 构建完整的 URL
            String url = "http://localhost:8082/sleep";

            // 创建 RestTemplate 对象
            RestTemplate restTemplate = new RestTemplate();

            // 发送 GET 请求
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // 打印响应结果
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
