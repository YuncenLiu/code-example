package com.liuyuncen.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordDocumentInsertion {
    public static void main(String[] args) {
        try {
            String document1Path = "/Users/xiang/Desktop/word1.docx"; // word1.docx文档路径
            String document2Path = "/Users/xiang/Desktop/project1.docx"; // word2.docx文档路径
            String merge = "/Users/xiang/Desktop/merge.docx"; // word2.docx文档路径

            // 打开word1.docx文档
            XWPFDocument document1 = new XWPFDocument(new FileInputStream(document1Path));
            // 打开word2.docx文档
            XWPFDocument document2 = new XWPFDocument(new FileInputStream(document2Path));

            // 复制word2.docx的内容到word1.docx中
            for (XWPFParagraph paragraph : document2.getParagraphs()) {
                XWPFParagraph newParagraph = document1.createParagraph();
                newParagraph.getCTP().setPPr(paragraph.getCTP().getPPr());
                for (XWPFRun run : paragraph.getRuns()) {
                    XWPFRun newRun = newParagraph.createRun();
                    newRun.getCTR().setRPr(run.getCTR().getRPr());
                    newRun.setText(run.getText(0));
                }
            }

            // 保存修改后的word1.docx文档
            FileOutputStream out = new FileOutputStream(merge);
            document1.write(out);
            out.close();

            System.out.println("内容插入成功！");
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}