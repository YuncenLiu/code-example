package com.liuyuncen.poiTlExt;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.ddr.poi.html.HtmlRenderPolicy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.poiTlExt
 * @author: Xiang想
 * @createTime: 2024-12-05  16:02
 * @description: TODO
 * @version: 1.0
 */
public class RichTextParserDemo {



    public static void main(String[] args) {
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        Configure configure = Configure.builder()
                .bind("Key", htmlRenderPolicy)
                .build();
        Map<String, Object> data = new HashMap<>();
        data.put("key", "<p>Hello <b>world</b>!</p>");
        try {
            XWPFTemplate.compile("/Users/xiang/Desktop/input.docx", configure).render(data).writeToFile("/Users/xiang/Desktop/output.docx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
