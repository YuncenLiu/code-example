package com.liuyuncen;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-09-05  10:41
 * @description: TODO
 * @version: 1.0
 */
public class ImageDownloadExample {
    public static void main(String[] args) throws IOException {
        // 图片的原始URL
        String imageUrl = "http://39.100.99.221:9000/lawyer/upload/20230901/c97bc70a05858002753c087e9fd3f17e.jpeg";

        // 下载原始图片
        File originalImage = downloadImage(imageUrl);

        // 压缩后的图片路径
        File compressedImage = new File("/Users/xiang/Desktop/compressed-image.jpg");

        // 压缩图片
        compressImage(originalImage, compressedImage);

        System.out.println("压缩完成，压缩后的图片保存在: " + compressedImage.getAbsolutePath());
    }

    // 下载图片到本地
    private static File downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        File imageFile = new File("/Users/xiang/Desktop/original-image.jpg");
        FileUtils.copyURLToFile(url, imageFile);
        return imageFile;
    }

    // 使用Guava库压缩图片
    private static void compressImage(File originalImage, File compressedImage) throws IOException {
        byte[] imageBytes = Files.toByteArray(originalImage);

        // 在这里进行图片压缩，例如，你可以使用压缩库来处理图像

        // 然后将压缩后的字节数组写入新文件
        Files.write(imageBytes, compressedImage);
    }
}
