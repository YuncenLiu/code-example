package com.liuyuncen;

import com.deepoove.poi.XWPFTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: test
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-17  11:37
 * @description: TODO
 * @version: 1.0
 */
public class XWPFTemplateTest {

    public static void main(String[] args) throws IOException {
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("title","Hi Poi-tl");
        datas.put("color","Hi Poi-tl");
        datas.put("color2","Hi Poi-tl");
        datas.put("color3","Hi Poi-tl");
        datas.put("date","Hi Poi-tl");
        datas.put("money","100");

//        // create table
//        RowRenderData header = Rows.of("Word处理方案", "是否跨平台", "易用性")
//                .textColor("FFFFFF")
//                .bgColor("ff9800")
//                .center()
//                .rowHeight(2.5f)
//                .create();
//        RowRenderData row0 = Rows.create("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
//        RowRenderData row1 = Rows.create("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
//        RowRenderData row2 = Rows.create("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
//        RowRenderData row3 = Rows.create("OpenOffice", "需要安装OpenOffice软件", "复杂，需要了解OpenOffice的API");
//        TableRenderData table = Tables.create(header, row0, row1, row2, row3);
//
//        // text
//        datas.put("header", "Deeply love what you love.");
//        datas.put("name", "Poi-tl");
//        datas.put("word", "模板引擎");
//        datas.put("time", "2020-08-31");
//        datas.put("what", "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java.");
//        datas.put("author", Texts.of("Sayi卅一").color("000000").create());
//
//        // hyperlink
//        datas.put("introduce", Texts.of("http://www.deepoove.com").link("http://www.deepoove.com").create());
//        // picture
//        datas.put("portrait", Pictures.ofLocal("src/test/resources/sayi.png").size(60, 60).create());
//        // table
//        datas.put("solution_compare", table);
//        // numbering
//        datas.put("feature",
//                Numberings.create("Plug-in grammar, add new grammar by yourself",
//                        "Supports word text, local pictures, web pictures, table, list, header, footer...",
//                        "Templates, not just templates, but also style templates"));
//
//        // chart
//        datas.put("chart",
//                Charts.ofMultiSeries("易用性", new String[] { "代码量", "维护量" })
//                        .addSeries("poi-tl", new Double[] { 10.0, 5.0 })
//                        .addSeries("freemark", new Double[] { 90.0, 70.0 })
//                        .create());

        XWPFTemplate.compile("/Users/xiang/Desktop/提交需求版--昆仑健康保险股份有限公司2023年一季度满期给付与退保报告.docx")
                .render(datas)
                .writeToFile("/Users/xiang/Desktop/提交需求版--昆仑健康保险股份有限公司2023年一季度满期给付与退保报告-desc.docx");
    }

}
