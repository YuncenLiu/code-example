package com.kunlun.sms;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * get token
 * create Date:2019-07-15
 * author:wangjianfeng
 */
public class GetTokenTest {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    public static void main(String[] args) {
        String userId = "1001310015";//ITSM调用账户
        String token = GetTokenTest.getToken(userId);//获取请求token
        System.out.println("token = " + token);
    }
    public static String getToken(String user_id) {
        String returnMsg = null;
        String token = null;
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("user_id", user_id);//调用帐户
        parameterMap.put("type", "1");//请求类型，设置为“1”
        parameterMap.put("TransDate", dateFormat.format(new Date()));
        parameterMap.put("TransTime", timeFormat.format(new Date()));
        String message = generateXml(parameterMap);
        System.out.println("message =>" +message);
        SmsServiceService smsServiceService = new SmsServiceService();
        SmsService smsService = smsServiceService.getSmsService();
        try {
            returnMsg = smsService.callWS(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(returnMsg);
        return  token;
    }
    private static String generateXml(Map<String, String> parameterMap) {
         Document doc = DocumentHelper.createDocument();
         Element sms = DocumentHelper.createElement("SMS");
         Element pub = DocumentHelper.createElement("PUB");
         pub.addElement("user_id").addText(parameterMap.get("user_id"));
         pub.addElement("type").addText(parameterMap.get("type"));
         pub.addElement("TransDate").addText(parameterMap.get("TransDate"));
         pub.addElement("TransTime").addText(parameterMap.get("TransTime"));
         sms.add(pub);
         doc.add(sms);
         return doc.asXML();
     }
}



@WebServiceClient(name = "SmsServiceService", targetNamespace = "http://servlet.sms.kunlun.com", wsdlLocation = "http://192.168.5.24/sms/services/SmsService?wsdl")
class SmsServiceService
        extends Service
{
    private final static URL SMSSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException SMSSERVICESERVICE_EXCEPTION;
    private final static QName SMSSERVICESERVICE_QNAME = new QName("http://servlet.sms.kunlun.com", "SmsServiceService");
    static {
        URL url = null;
        WebServiceException e = null;
        try {
            //   url = new URL("http://10.5.3.109:7001/sms/services/SmsService?wsdl");
            url = new URL("http://192.168.5.24/sms/services/SmsService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SMSSERVICESERVICE_WSDL_LOCATION = url;
        SMSSERVICESERVICE_EXCEPTION = e;
    }
    public SmsServiceService() {
        super(__getWsdlLocation(), SMSSERVICESERVICE_QNAME);
    }
    @WebEndpoint(name = "SmsService")
    public SmsService getSmsService() {
        return super.getPort(new QName("http://servlet.sms.kunlun.com", "SmsService"), SmsService.class);
    }
    private static URL __getWsdlLocation() {
        if (SMSSERVICESERVICE_EXCEPTION!= null) {
            throw SMSSERVICESERVICE_EXCEPTION;
        }
        return SMSSERVICESERVICE_WSDL_LOCATION;
    }
}


@WebService(name = "SmsService", targetNamespace = "http://servlet.sms.kunlun.com")
interface SmsService {
    @WebMethod
    @WebResult(name = "callWSReturn", targetNamespace = "http://servlet.sms.kunlun.com")
    String callWS(String message);
}
