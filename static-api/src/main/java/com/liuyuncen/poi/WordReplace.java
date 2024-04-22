package com.liuyuncen.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.poi
 * @author: Xiang想
 * @createTime: 2024-02-26  13:00
 * @description: TODO
 * @version: 1.0
 */
public class WordReplace {
    public static void main(String[] args) {
        try {
            // 读取 Word 文档
            FileInputStream fis = new FileInputStream("/Users/xiang/Desktop/临时文件夹/经营性分析测试文档/1.docx");
            XWPFDocument document = new XWPFDocument(fis);

            // 遍历文档中的段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    System.out.println("text = " + text);
//                    if (text != null && text.contains("pch")) {
//                        text = text.replace("pch", "123");
//                        run.setText(text, 0);
//                    }
                }
            }

            // 保存修改后的文档
//            FileOutputStream fos = new FileOutputStream("/Users/xiang/Desktop/2.docx");
//            document.write(fos);

            document.close();
            fis.close();
//            fos.close();

            System.out.println("替换完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
