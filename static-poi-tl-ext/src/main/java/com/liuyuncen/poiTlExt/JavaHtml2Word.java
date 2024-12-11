package com.liuyuncen.poiTlExt;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.spire.doc.Document;
import com.spire.doc.DocumentObject;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ddr.poi.html.HtmlRenderPolicy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaHtml2Word示例
 *
 * @author wangdy
 * 2023-06-02 11:24:28
 */
public class JavaHtml2Word {


    public static void main(String[] args) throws IOException {
        // html渲染插件
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        // 第一个案例
        Configure configure = Configure.builder()
                // 注册html解析插件
                .bind("content", htmlRenderPolicy)
                // .bind("content2", htmlRenderPolicy)
                .build();
        // 映射数据Map
        Map<String, Object> data = new HashMap<>();
        data.put("title", "我是一个案例名称1");
        data.put("keywords", "我是一个案例keywords");
        data.put("cty", "我是一个案例cty");
        data.put("content", content2Html(readFile("/Users/xiang/Desktop/csdn01.html")));
        // 读取模板文件，并渲染数据
        XWPFTemplate template = XWPFTemplate.compile(getResourceInputStream("/Users/xiang/Desktop/html2wordtemplate.docx"), configure).render(data);
        // 写入文件
        template.writeToFile("demo1.docx");
        template.close();

        // 第二个案例
        Configure configure1 = Configure.builder().bind("content", htmlRenderPolicy).build();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("title", "我是一个案例名称2");
        data1.put("keywords", "我是一个案例keywords2");
        data1.put("cty", "我是一个案例分类2");
        data1.put("content", content2Html(readFile("/Users/xiang/Desktop/demo2.html")));
        XWPFTemplate template1 = XWPFTemplate.compile(getResourceInputStream("/Users/xiang/Desktop/html2wordtemplate.docx"), configure1).render(data1);
        template1.writeToFile("demo2.docx");
        template1.close();

        // 合并word
        // 加载需要合并的两个文档
        Document doc1 = new Document("demo1.docx");
        Document doc2 = new Document("demo2.docx");
        // 获取文档1的最后一节
        Section lastsec = doc1.getLastSection();
        // 遍历文档2的所有段落内容，添加到文档1
        for (Section section : (Iterable<Section>) doc2.getSections()) {
            for (DocumentObject obj : (Iterable<DocumentObject>) section.getBody().getChildObjects()) {
                lastsec.getBody().getChildObjects().add(obj.deepClone());
            }
        }
        // 保存合并后的文档
        doc1.saveToFile("/Users/xiang/Desktop/ALL-Word.docx", FileFormat.Docx);
        System.out.println("合并完成");


        // csdn01.html转换测试
        // HtmlRenderPolicy htmlRenderPolicy3 = new HtmlRenderPolicy();
        // Configure configure3 = Configure.builder()
        //         .bind("content", htmlRenderPolicy3)
        //         .build();
        // Map<String, Object> data3 = new HashMap<>();
        // data3.put("title", "我是一个案例名称3");
        // data3.put("keywords", "我是一个案例keywords");
        // data3.put("cty", "我是一个案例cty3");
        // data3.put("content", content2Html(readFile("/csdn01.html")));
        // XWPFTemplate template3 = XWPFTemplate.compile(getResourceInputStream("/html2wordtemplate.docx"), configure3).render(data3);
        // template3.writeToFile("csdn01.docx");
        // template3.close();
    }

    /**
     * 获取资源文件的文件流
     *
     * @return InputStream
     */
    public static InputStream getResourceInputStream(String filePath) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (in != null) {
            return in;
        }
        return null;
    }

    /**
     * 读取文件内容
     *
     * @param resourceFile 文件路径
     * @return 文件内容
     * @throws IOException IO异常 import org.apache.commons.io.IOUtils
     */
    public static String readFile(String resourceFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(resourceFile))) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    /**
     * 替换html td标签
     *
     * @param content String
     * @return String
     */
    protected static String content2Html(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }
        String result = content.replaceAll("<br>", "<br/>")
                .replaceAll("<table ", "<table width='490' border='0' cellspacing='0' cellpadding='0' style='width:490px!important; border-right:1px solid #CCC;border-bottom:1px solid #CCC' ")
                .replaceAll("<table>", "<table width='490' border='0' cellspacing='0' cellpadding='0' style='width:490px!important; border-right:1px solid #CCC;border-bottom:1px solid #CCC'> ")
                .replaceAll("<td ", "<td style='border-left:1px solid #CCC;border-top:1px solid #CCC ' ")
                .replaceAll("<td>", "<td style='border-left:1px solid #CCC;border-top:1px solid #CCC '> ");
        // log.info("@@处理后的html@@" + result);
        return result;
    }
}
