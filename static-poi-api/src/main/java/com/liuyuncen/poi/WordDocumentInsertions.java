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
        try {

            List<String> pathLists = new ArrayList<>();
            pathLists.add("/Users/xiang/Desktop/1.docx");
            pathLists.add("/Users/xiang/Desktop/2.docx");
            pathLists.add("/Users/xiang/Desktop/3.docx");
            pathLists.add("/Users/xiang/Desktop/4.docx");


            String source = "/Users/xiang/Desktop/merge.docx"; // word2.docx文档路径
            XWPFDocument sourceDocument = new XWPFDocument(new FileInputStream(source));


            List<XWPFDocument> documentList = new ArrayList<>();
            for (String pathList : pathLists) {
                documentList.add(new XWPFDocument(new FileInputStream(pathList)));
            }


            for (XWPFDocument xwpfDocument : documentList) {
                for (XWPFParagraph paragraph : xwpfDocument.getParagraphs()) {
                    XWPFParagraph newParagraph = sourceDocument.createParagraph();
                    newParagraph.getCTP().setPPr(paragraph.getCTP().getPPr());
                    for (XWPFRun run : paragraph.getRuns()) {
                        XWPFRun newRun = newParagraph.createRun();
                        newRun.getCTR().setRPr(run.getCTR().getRPr());
                        newRun.setText(run.getText(0));
                    }
                }
            }

            // 保存修改后的word1.docx文档
            FileOutputStream out = new FileOutputStream(source);
            sourceDocument.write(out);
            out.close();

            System.out.println("内容插入成功！");
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}