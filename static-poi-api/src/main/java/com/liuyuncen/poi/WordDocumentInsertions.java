package com.liuyuncen.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordDocumentInsertions {

    public static void main(String[] args) {

        List<String> pathLists = new ArrayList<>();
        pathLists.add("/Users/xiang/Desktop/1.docx");
        pathLists.add("/Users/xiang/Desktop/2.docx");
        pathLists.add("/Users/xiang/Desktop/3.docx");
        pathLists.add("/Users/xiang/Desktop/4.docx");

        String mergePath = "/Users/xiang/Desktop/merge.docx";

        try {
            // 创建一个空的合并文档
            XWPFDocument mergeDoc = new XWPFDocument();

            // 遍历所有输入文件路径
            for (String filePath : pathLists) {
                FileInputStream fis = new FileInputStream(filePath);
                XWPFDocument doc = new XWPFDocument(fis);

                // 遍历输入文档的段落
                List<XWPFParagraph> paragraphs = doc.getParagraphs();
                for (XWPFParagraph paragraph : paragraphs) {
                    // 复制段落到合并文档中
                    XWPFParagraph newParagraph = mergeDoc.createParagraph();
                    newParagraph.getCTP().setPPr(paragraph.getCTP().getPPr());
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        XWPFRun newRun = newParagraph.createRun();
                        newRun.getCTR().setRPr(run.getCTR().getRPr());
                        newRun.setText(run.getText(0));
                    }
                }

                fis.close();
                doc.close();
            }

            // 将合并后的文档保存到目标路径
            FileOutputStream fos = new FileOutputStream(mergePath);
            mergeDoc.write(fos);

            fos.close();
            mergeDoc.close();

            System.out.println("合并完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}