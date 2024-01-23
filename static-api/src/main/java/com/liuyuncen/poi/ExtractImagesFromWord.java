package com.liuyuncen.poi;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExtractImagesFromWord {
    public static void main(String[] args) {
        try {
            String documentPath = "/Users/xiang/Desktop/1-1.docx"; // word2.docx文档路径

            // 打开word2.docx文档
            XWPFDocument document = new XWPFDocument(new FileInputStream(documentPath));

            // 提取word2.docx中的所有图片
            int imageIndex = 1;
            for (XWPFPictureData pictureData : document.getAllPictures()) {
                byte[] imageData = pictureData.getData();
                String imageExtension = pictureData.suggestFileExtension();

                String imageName = "/Users/xiang/Desktop/image" + imageIndex + "." + imageExtension;
                FileOutputStream out = new FileOutputStream(imageName);
                IOUtils.copy(new ByteArrayInputStream(imageData), out);
                out.close();

                System.out.println("图片" + imageIndex + "已提取：" + imageName);
                imageIndex++;
            }

            System.out.println("图片提取完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}