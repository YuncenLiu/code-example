package com.liuyuncen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-09-15  15:01
 * @description: TODO
 * @version: 1.0
 */
public class XmlParserExample {
    public static void main(String[] args) {
        try {
            // 创建一个 DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 解析XML文件
            Document document = builder.parse(new File("/Users/xiang/Desktop/xml/20230911.xml"));

            // 获取根元素
            Element root = document.getDocumentElement();

            // 获取 Control 元素下的子元素
            NodeList controlElements = root.getElementsByTagName("Control");
            Element controlElement = (Element) controlElements.item(0);

            // 提取 Control 元素下的子元素的值
            String checkJob = controlElement.getElementsByTagName("CheckJob").item(0).getTextContent();
            String docCount = controlElement.getElementsByTagName("DocCount").item(0).getTextContent();
            String outSource = controlElement.getElementsByTagName("OutSource").item(0).getTextContent();
            String createTime = controlElement.getElementsByTagName("CreateTime").item(0).getTextContent();

            System.out.println("CheckJob: " + checkJob);
            System.out.println("DocCount: " + docCount);
            System.out.println("OutSource: " + outSource);
            System.out.println("CreateTime: " + createTime);

            // 获取 DocInfos 元素下的子元素
            NodeList docInfoElements = root.getElementsByTagName("DocInfo");

            for (int i = 0; i < docInfoElements.getLength(); i++) {
                Element docInfoElement = (Element) docInfoElements.item(i);

                String fileUploadTime = docInfoElement.getElementsByTagName("FileUploadTime").item(0).getTextContent();
                String contNo = docInfoElement.getElementsByTagName("ContNo").item(0).getTextContent();
                String expressNO = docInfoElement.getElementsByTagName("ExpressNO").item(0).getTextContent();
                String receiver = docInfoElement.getElementsByTagName("Receiver").item(0).getTextContent();
                String address = docInfoElement.getElementsByTagName("Address").item(0).getTextContent();
                String deliverTime = docInfoElement.getElementsByTagName("DeliverTime").item(0).getTextContent();
                String pickupTime = docInfoElement.getElementsByTagName("PickupTime").item(0).getTextContent();
                String status = docInfoElement.getElementsByTagName("Status").item(0).getTextContent();

                System.out.println("DocInfo " + (i + 1) + ":");
                System.out.println("FileUploadTime: " + fileUploadTime);
                System.out.println("ContNo: " + contNo);
                System.out.println("ExpressNO: " + expressNO);
                System.out.println("Receiver: " + receiver);
                System.out.println("Address: " + address);
                System.out.println("DeliverTime: " + deliverTime);
                System.out.println("PickupTime: " + pickupTime);
                System.out.println("Status: " + status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
