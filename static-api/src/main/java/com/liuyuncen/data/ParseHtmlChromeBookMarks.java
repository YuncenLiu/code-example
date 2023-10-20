package com.liuyuncen.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.data
 * @author: Xiang想
 * @createTime: 2023-09-28  17:35
 * @description: TODO
 * @version: 1.0
 */
public class ParseHtmlChromeBookMarks {
    public static void main(String[] args) {
        Set<String> sets = new HashSet<>();
        try {
            // 使用Jsoup解析HTML
            File input = new File("/Users/xiang/Desktop/bookmarks_2023_9_28.html"); // 替换为你的HTML文件的实际路径
            Document doc = Jsoup.parse(input, "UTF-8");


            // 获取所有<a>标签
            Elements links = doc.select("a");

            // 遍历所有<a>标签并获取内容
            int i = 0;
            for (Element link : links) {
                i++;
                String linkText = link.text();
                String linkHref = link.attr("href");

                linkText = linkText.trim().replaceAll("\\|","-");
//                System.out.println("链接文本：" + linkText);
//                System.out.println("链接地址：" + linkHref);
                String result = linkText + "," + linkHref;
                sets.add(result);
            }
            System.out.println("i = " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        for (String set : sets) {
            i ++;
            System.out.println(i + ","+ set);
        }
        System.out.println("sets.size() = " + sets.size());
    }
}
