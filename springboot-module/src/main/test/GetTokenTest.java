import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: PACKAGE_NAME
 * @author: Xiang想
 * @createTime: 2024-11-20  14:12
 * @description: TODO
 * @version: 1.0
 */
public class GetTokenTest {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        String userId = "1001310015"; // ITSM调用账户
        String token = GetTokenTest.getToken(userId); // 获取请求token
        System.out.println("token = " + token);
    }

    public static String getToken(String user_id) {
        String returnMsg = null;
        String token = null;
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("user_id", user_id); // 调用帐户
        parameterMap.put("type", "1"); // 请求类型，设置为“1”
        parameterMap.put("TransDate", dateFormat.format(new Date()));
        parameterMap.put("TransTime", timeFormat.format(new Date()));
        String message = generateSoapEnvelope(parameterMap);
        System.out.println("message =>" + message);

        try {
            returnMsg = sendPostRequest(message);
            System.out.println("returnMsg => " + returnMsg);
            token = extractTokenFromResponse(returnMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    private static String generateSoapEnvelope(Map<String, String> parameterMap) {
        Document doc = DocumentHelper.createDocument();
        Element envelope = doc.addElement(QName.get("Envelope", "http://schemas.xmlsoap.org/soap/envelope/"))
                .addNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/")
                .addNamespace("xsd", "http://www.w3.org/2001/XMLSchema")
                .addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element body = envelope.addElement(QName.get("Body", "http://schemas.xmlsoap.org/soap/envelope/"));
        Element callWS = body.addElement(QName.get("callWS", "http://servlet.sms.kunlun.com"));

        Element sms = callWS.addElement("SMS");
        Element pub = sms.addElement("PUB");
        pub.addElement("user_id").addText(parameterMap.get("user_id"));
        pub.addElement("type").addText(parameterMap.get("type"));
        pub.addElement("TransDate").addText(parameterMap.get("TransDate"));
        pub.addElement("TransTime").addText(parameterMap.get("TransTime"));

        return doc.asXML();
    }

    private static String sendPostRequest(String message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", ""); // 设置SOAPAction头为空字符串

        HttpEntity<String> request = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://192.168.5.24/sms/services/SmsService", request, String.class);

        return response.getBody();
    }

    private static String extractTokenFromResponse(String response) {
        // 假设响应中包含 <callWSReturn>...</callWSReturn> 标签
        int start = response.indexOf("<callWSReturn>") + "<callWSReturn>".length();
        int end = response.indexOf("</callWSReturn>");
        if (start > 0 && end > start) {
            return response.substring(start, end);
        }
        return null;
    }
}