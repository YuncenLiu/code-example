package com.liuyuncen.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordDocumentInsertion {
    public static void main(String[] args) {
        try {
            String document1Path = "/Users/xiang/Desktop/word1.docx"; // word1.docx文档路径
            String document2Path = "/Users/xiang/Desktop/project.docx"; // word2.docx文档路径

            // 打开word1.docx文档
            XWPFDocument document1 = new XWPFDocument(new FileInputStream(document1Path));

            // 打开word2.docx文档
            XWPFDocument document2 = new XWPFDocument(new FileInputStream(document2Path));

            // 插入word2.docx的内容到word1.docx的末尾
            for (XWPFParagraph paragraph : document2.getParagraphs()) {
                XWPFParagraph newParagraph = document1.createParagraph();
                newParagraph.getCTP().setPPr(paragraph.getCTP().getPPr());
                for (XWPFRun run : paragraph.getRuns()) {
                    XWPFRun newRun = newParagraph.createRun();
                    newRun.getCTR().setRPr(run.getCTR().getRPr());
                    newRun.setText(run.getText(0));
                }
            }

            // 复制word2.docx的图片到word1.docx的末尾
            for (XWPFPictureData pictureData : document2.getAllPictures()) {
                byte[] bytes = pictureData.getData();
                String filename = pictureData.getFileName();
                int pictureType = pictureData.getPictureType();

                int width = -1; // 使用图片的原始宽度
                int height = -1; // 使用图片的原始高度

                XWPFParagraph newParagraph = document1.createParagraph();
                XWPFRun newRun = newParagraph.createRun();

                // 将图片文件的路径设置为相对路径
                newRun.addPicture(new FileInputStream("/Users/xiang/Desktop/" + filename), pictureType, filename, width, height);
            }

            // 复制word2.docx的表格到word1.docx的末尾
            List<XWPFTable> tables = document2.getTables();
            for (XWPFTable table : tables) {
                XWPFTable newTable = document1.createTable();
                newTable.getCTTbl().setTblPr(table.getCTTbl().getTblPr());
                for (XWPFTableRow row : table.getRows()) {
                    XWPFTableRow newRow = newTable.createRow();
                    for (XWPFTableCell cell : row.getTableCells()) {
                        XWPFTableCell newCell = newRow.createCell();
                        newCell.getCTTc().setTcPr(cell.getCTTc().getTcPr());
                        newCell.setText(cell.getText());
                    }
                }
            }

            // 保存修改后的word1.docx文档
            FileOutputStream out = new FileOutputStream(document1Path);
            document1.write(out);
            out.close();

            System.out.println("内容插入成功！");
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
