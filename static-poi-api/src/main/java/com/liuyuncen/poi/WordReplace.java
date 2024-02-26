package com.liuyuncen.poi;

import com.deepoove.poi.XWPFTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.poi
 * @author: Xiang想
 * @createTime: 2024-02-26  13:00
 * @description: TODO
 * @version: 1.0
 */
public class WordReplace {
    public static void main(String[] args) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("/Users/xiang/Desktop/1.docx").render(
                new HashMap<String, Object>(){{
                    put("pch", "2023年第一季度");
                }});
        template.writeAndClose(Files.newOutputStream(Paths.get("/Users/xiang/Desktop/2.docx")));
    }
}
