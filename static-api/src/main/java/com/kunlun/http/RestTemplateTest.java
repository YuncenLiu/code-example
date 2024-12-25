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
public class RestTemplateTest {
    public static void main(String[] args) {
        try {
            // 设置请求的 URL 和查询参数
            String timestamp = "2024-12-23%2010:25:52";
            String orgCode = "dev_s_meixin_sftp";
            String dataTypeCode = "SP";

            // 对 timestamp 进行 URL 编码，确保空格被转义

            // 构建完整的 URL
            String url = "http://10.5.3.176:8082/api/xiang-sftp-password-interface/sftp-password-trajectory/query-password" +
                    "?timestamp=" + timestamp +
                    "&orgCode=" + orgCode +
                    "&dataTypeCode=" + dataTypeCode;

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
