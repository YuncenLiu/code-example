package com.liuyuncen;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-09-05  10:49
 * @description: TODO
 * @version: 1.0
 */
public class ImageCompressionExample {
    public static void main(String[] args) throws IOException {
        // 输入图片文件路径
        String inputImagePath = "/Users/xiang/Desktop/original-image.jpg";

        // 输出图片文件路径
        String outputImagePath = "/Users/xiang/Desktop/compressed-image-4.jpg";

        // 设置压缩质量（0.0 最差，1.0 最佳）
        float quality = 0.7f;

        // 调用压缩方法
        compressImage(inputImagePath, outputImagePath, quality);

        System.out.println("压缩完成，压缩后的图片保存在: " + outputImagePath);
    }

    // 图片压缩方法
    private static void compressImage(String inputImagePath, String outputImagePath, float quality) throws IOException {
        File inputFile = new File(inputImagePath);
        File outputFile = new File(outputImagePath);

        BufferedImage bufferedImage = ImageIO.read(inputFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();

        ImageWriteParam writeParam = new JPEGImageWriteParam(null);
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        writeParam.setCompressionQuality(quality);

        FileImageOutputStream output = new FileImageOutputStream(outputFile);
        writer.setOutput(output);

        IIOImage image = new IIOImage(bufferedImage, null, null);

        writer.write(null, image, writeParam);

        writer.dispose();
        output.close();
    }
}
