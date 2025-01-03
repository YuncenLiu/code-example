package com.kunlun.zip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mjw
 * @version 0.0
 * @date 2025/1/2 9:56
 **/
public class SendPostMain {

    static final String key = "meixin1234567899"; // 16 字节 AES 密钥
    static final String iv = "1234567812345678"; // 16 字节 IV

    public static void main(String[] args) throws Exception{

        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = sim.format(new Date());

        // 构建请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chnlCode","meixin");
        jsonObject.put("serviceCode","getSFTPpassword");
        jsonObject.put("timestamp",nowTime);

        JSONObject reqMsgJson = new JSONObject();
        reqMsgJson.put("timestamp","2024-01-23 10:25:52");
        reqMsgJson.put("orgCode","dev_s_meixin_sftp");
        reqMsgJson.put("dataTypeCode","SP");
        jsonObject.put("reqMsg",reqMsgJson);


        String reqMsg = jsonObject.getString("reqMsg");
        String encrypt = AESExample.encrypt(reqMsg, key, iv);

        // API URL
        String url = "https://testapi.kunlunhealth.com.cn/service";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        jsonObject.put("reqMsg", encrypt);
        String requestBody = jsonObject.toJSONString();

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 返回响应体内容
        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            JSONObject responseJson = JSON.parseObject(body);
            String responseEncrypt = responseJson.get("respMsg").toString();
            String decryptJson = AESExample.decrypt(responseEncrypt, key, iv);
            responseJson.put("respMsg",decryptJson);
            System.out.println(responseJson.toJSONString());

        } else {
            System.out.println("Request failed with status: " + response.getStatusCode());
        }
    }

}
