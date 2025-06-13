package com.kunlun.vpnItsm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @belongsProject: Xiang-Cloud
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-11-14  09:16
 * @description: TODO
 * @version: 1.0
 */
public class VpnResponseTest {
    public static void main(String[] args) {


//        addYuncen("liuyuncen_test_1");
//        updateYuncen();
//        updateYuncen("liuyuncen_test_1");
//        getRole();
//        updateYuncen();
//        queryYuncen("liuyuncen_test");
//        queryYuncen("liuyuncen_test_1");
//        queryYuncen("liuyuncen_test_2");
//        queryYuncen("liuyuncen_test_3");
        query2("liuyuncen_test_1");
//        query2("test_wangyepeng2");

        // 1734316081
        // 1734316100
    }

    public static void getRole(){
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);  // 当前时间戳（秒）

        String secretKey = "a2dbedc265222b380c735f43d9cbef93";
        String start = "0";
        String limit = "500";
        String paramsPost = "start=" + start
                + "&limit=" + limit;
        String paramsGet = "controler=Role&action=GetRoleListDataCloud";
        String params = paramsPost + "&" + paramsGet + "&timestamp=" +  timestamp;

        String sortedParams = sortParams(params);

        System.out.println(sortedParams + " - " + timestamp + " - " + secretKey);
        String token = generateToken(sortedParams, timestamp, secretKey);


        // 构建表单数据
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("timestamp",timestamp );
        map.add("sinfor_apitoken", token);
        map.add("start", start);
        map.add("limit", limit);

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建 HttpEntity 对象，包含表单数据和请求头
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        // 发送 POST 请求
        String url = "https://10.252.240.6:4430/cgi-bin/php-cgi/html/delegatemodule/WebApi.php?"+paramsGet;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        JSONObject jsonObject = JSON.parseObject(response.getBody());
        String message = jsonObject.getString("message");
        System.out.println(message);

        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray data = result.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject jo = data.getJSONObject(i);
            String name = jo.getString("name");
            System.out.println(name);

        }


        // 输出响应
        System.out.println("Response: " + response.getBody());
    }

    public static void updateYuncen(String name){
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);  // 当前时间戳（秒）
        String old_name = name;
        String new_name = name;
        String secretKey = "a2dbedc265222b380c735f43d9cbef93";
        String parent_group = "/昆仑健康保险/总公司/中介业务部/经代业务处";
        String note = "随创-刘云岑-测试-4";
        String passwd = "Lyc!23456";
        String phone = "15279151605";
        String gqsj = "0";
        String ex_time = "2024-12-30";
//        String role_name = "1000";
//        String role_name = "LIS";
        String role_name = "159-电子签章系统[-]";
//        String role_name = "[LIS]";
//        String role_name = "核心业务系统";
//        String roleid = "1060";

        // 构建请求参数
        String paramsPost = "old_name=" + old_name
                + "&new_name=" + new_name
                + "&timestamp=" + timestamp
                + "&parent_group=" + parent_group
                + "&role_name=" + role_name
                + "&note=" + note
                + "&passwd=" + passwd
                + "&phone=" + phone
                + "&gqsj=" + gqsj
                + "&ex_time=" + ex_time;
        String paramsGet = "controler=User&action=UpdateUserCloud";
        String params = paramsPost + "&" + paramsGet;

        // 排序参数并计算签名
        String sortedParams = sortParams(params);

        System.out.println(sortedParams + " - " + timestamp + " - " + secretKey);
        String token = generateToken(sortedParams, timestamp, secretKey);


        // 构建表单数据
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("timestamp",timestamp );
        map.add("sinfor_apitoken", token);
        map.add("old_name", old_name);
        map.add("new_name", new_name);
        map.add("parent_group", parent_group);
        map.add("note", note);
        map.add("passwd", passwd);
        map.add("role_name", role_name);
        map.add("phone", phone);
        map.add("gqsj", gqsj);
        map.add("ex_time", ex_time);

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建 HttpEntity 对象，包含表单数据和请求头
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        // 发送 POST 请求
        String url = "https://10.252.240.6:4430/cgi-bin/php-cgi/html/delegatemodule/WebApi.php?controler=User&action=UpdateUserCloud";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        JSONObject jsonObject = JSON.parseObject(response.getBody());
        String message = jsonObject.getString("message");
        System.out.println(message);

        // 输出响应
        System.out.println("Response: " + response.getBody());
    }

    public static void addYuncen(String name){
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);  // 当前时间戳（秒）
        String secretKey = "a2dbedc265222b380c735f43d9cbef93";
        String parent_group = "/组织架构外/信息技术部外勤";
        String note = "随创-刘云岑-测试";
        String passwd = "Lyc!23456";
        String phone = "15279151605";
        String gqsj = "0";
        String ex_time = "2024-11-30";

        // 构建请求参数
        String paramsPost = "name=" + name
                + "&timestamp=" + timestamp
                + "&parent_group=" + parent_group
                + "&note=" + note
                + "&passwd=" + passwd
                + "&phone=" + phone
                + "&gqsj=" + gqsj
                + "&ex_time=" + ex_time;
        String paramsGet = "controler=User&action=AddUserCloud";
        String params = paramsPost + "&" + paramsGet;

        // 排序参数并计算签名
        String sortedParams = sortParams(params);

        System.out.println(sortedParams + " - " + timestamp + " - " + secretKey);
        String token = generateToken(sortedParams, timestamp, secretKey);


        // 构建表单数据
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("timestamp",timestamp );
        map.add("sinfor_apitoken", token);
        map.add("name", name);
        map.add("parent_group", parent_group);
        map.add("note", note);
        map.add("passwd", passwd);
        map.add("phone", phone);
        map.add("gqsj", gqsj);
        map.add("ex_time", ex_time);

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建 HttpEntity 对象，包含表单数据和请求头
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);


        // 发送 POST 请求
        String url = "https://10.252.240.6:4430/cgi-bin/php-cgi/html/delegatemodule/WebApi.php?controler=User&action=AddUserCloud";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 输出响应

        // 正则表达式，匹配  后的 4 位十六进制数字
        Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher matcher = pattern.matcher(response.getBody());

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            // 获取匹配到的十六进制值
            String hexValue = matcher.group(1);
            // 将十六进制值转换为字符
            char ch = (char) Integer.parseInt(hexValue, 16);
            // 替换为对应的字符
            matcher.appendReplacement(result, String.valueOf(ch));
        }

        // 完成替换后的字符串
        matcher.appendTail(result);

        System.out.println("Response: " + result.toString());

    }

    public static void query2(String name){
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);  // 当前时间戳（秒）
        String username = name;
        String secretKey = "a2dbedc265222b380c735f43d9cbef93";

        // 构建请求参数
        String paramsPost = "username=" + username
                + "&timestamp=" + timestamp;
        String paramsGet = "controler=User&action=ExGetUserInfo";
        String params = paramsPost + "&" + paramsGet;

        // 排序参数并计算签名
        String sortedParams = sortParams(params);

        System.out.println(sortedParams + " - " + timestamp + " - " + secretKey);
        String token = generateToken(sortedParams, timestamp, secretKey);
        System.out.println("token = " + token);

        // 构建表单数据
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("timestamp",timestamp );
        map.add("sinfor_apitoken", token);
        map.add("username", username);

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建 HttpEntity 对象，包含表单数据和请求头
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        // 发送 POST 请求
        String url = "https://10.252.240.6:4430/cgi-bin/php-cgi/html/delegatemodule/WebApi.php?controler=User&action=ExGetUserInfo";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 输出响应

        // 正则表达式，匹配  后的 4 位十六进制数字
        Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher matcher = pattern.matcher(response.getBody());

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            // 获取匹配到的十六进制值
            String hexValue = matcher.group(1);
            // 将十六进制值转换为字符
            char ch = (char) Integer.parseInt(hexValue, 16);
            // 替换为对应的字符
            matcher.appendReplacement(result, String.valueOf(ch));
        }

        // 完成替换后的字符串
        matcher.appendTail(result);

        System.out.println("Response: " + result.toString());
    }

    public static void queryYuncen(String name){
        String sendUrl = "https://10.252.240.6:4430/cgi-bin/php-cgi/html/delegatemodule/WebApi.php?controler=User&action=ExGetUserInfo";
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);  // 当前时间戳（秒）
        String username = name;
        String secretKey = "a2dbedc265222b380c735f43d9cbef93";

        // 构建请求参数
        String paramsPost = "username=" + username + "&timestamp=" + timestamp;
        String paramsGet = "controler=User&action=ExGetUserInfo";
        String params = paramsPost + "&" + paramsGet;

        // 排序参数并计算签名
        String sortedParams = sortParams(params);

        System.out.println(sortedParams + " - " + timestamp + " - " + secretKey);
        String token = generateToken(sortedParams, timestamp, secretKey);

        // 将 token 添加到 POST 参数中
        String paramsPostWithToken = paramsPost + "&sinfor_apitoken=" + token;

        System.out.println(token);


        // 使用 RestTemplate 发送请求
        boolean success = sendPostRequestWithRetry(sendUrl, paramsPostWithToken, 3);
        if (!success) {
            System.err.println("请求失败，已重试 3 次，仍未成功！");
        }
    }

    public static void queryLiyan(){
        String sendUrl = "https://10.252.240.6:4430/cgi-bin/php-cgi/html/delegatemodule/WebApi.php?controler=User&action=ExGetUserInfo";
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);  // 当前时间戳（秒）
        String username = "liyan_1";
        String secretKey = "a2dbedc265222b380c735f43d9cbef93";

        // 构建请求参数
        String paramsPost = "username=" + username + "&timestamp=" + timestamp;
        String paramsGet = "controler=User&action=ExGetUserInfo";
        String params = paramsPost + "&" + paramsGet;

        // 排序参数并计算签名
        String sortedParams = sortParams(params);

        System.out.println(sortedParams + " - " + timestamp + " - " + secretKey);
        String token = generateToken(sortedParams, timestamp, secretKey);

        // 将 token 添加到 POST 参数中
        String paramsPostWithToken = paramsPost + "&sinfor_apitoken=" + token;

        System.out.println(token);


        // 使用 RestTemplate 发送请求
        boolean success = sendPostRequestWithRetry(sendUrl, paramsPostWithToken, 3);
        if (!success) {
            System.err.println("请求失败，已重试 3 次，仍未成功！");
        }
    }





    // 排序参数
    private static   String sortParams(String params) {
        // 将参数按 '&' 分割，然后排序
        String[] paramArray = params.split("&");
        Arrays.sort(paramArray);

        // 拼接成排序后的参数字符串
        StringBuilder sortedParams = new StringBuilder();
        for (String param : paramArray) {
            sortedParams.append(param).append("&");
        }

        // 去掉最后一个 '&'
        if (sortedParams.length() > 0) {
            sortedParams.setLength(sortedParams.length() - 1);
        }

        return sortedParams.toString();
    }

    // 生成 API Token
    private static   String generateToken(String sortedParams, String timestamp, String secretKey) {
        try {
            // 拼接请求的参数、时间戳和密钥
            String data = sortedParams + timestamp + secretKey;

            // 计算 SHA-256 哈希
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            // 转换成十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static   boolean sendPostRequestWithRetry(String url, String params, int maxRetries) {
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(params, headers);

        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                attempt++;
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                VpnResponseEntity vpnResponseEntity = JSON.parseObject(response.getBody(), VpnResponseEntity.class);
                System.out.println("请求成功，响应: " + vpnResponseEntity);

                return true;  // 请求成功，返回 true
            } catch (Exception e) {
                // 请求失败，打印错误信息并准备重试
                System.err.println("请求失败，第 " + attempt + " 次尝试: " + e.getMessage());
                if (attempt == maxRetries) {
                    // 如果达到最大重试次数，输出错误日志
                    System.err.println("请求失败，已重试 " + maxRetries + " 次，仍未成功！");
                }
            }
        }
        return false;  // 如果超过最大重试次数，返回 false
    }
}
