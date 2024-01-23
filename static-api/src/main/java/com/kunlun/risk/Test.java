package com.kunlun.risk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.risk
 * @author: Xiang想
 * @createTime: 2024-01-05  16:00
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        String markFilePath = "/Users/xiang/Desktop/mark.txt";
        String index1Path = "/Users/xiang/Desktop/index_1.txt";
        String index2Path = "/Users/xiang/Desktop/index_2.txt";

        List<String> arr = test.readTxt(markFilePath);
        List<String> index1List = test.readTxt(index1Path);
        List<String> index2List = test.readTxt(index2Path);

        Map<String, String> dict = new HashMap<>();
        if (index1List.size() == index2List.size()) {
            for (int i = 0; i < index1List.size(); i++) {
                dict.put(index1List.get(i),index2List.get(i));
            }
        }

        System.out.println("arr = "+ arr.size());
        System.out.println("dict = " + dict.size());

        List<String> black = test.black(arr, dict);
        System.out.println("black = " + black.size());
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }

        for (String s : black) {
            System.out.println(s);
        }


    }

    public List<String> black( List<String> arr,Map<String, String> dict){
        List<String> result = new ArrayList<>();
        for (String s : arr) {
            try {
                result.add(loopReplace(s,dict));
            } catch (Exception e) {
                System.out.println("=========> " + s);
                result.add(s);
            }

        }
        return result;
    }


    public List<String> readTxt(String filePath) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String loopReplace(String source,Map<String, String> dict) throws Exception{
        Pattern pattern = Pattern.compile("#(.*?)#");
        Matcher matcher = pattern.matcher(source);

        List<String> findBy = new ArrayList<>();
        while (matcher.find()) {
            findBy.add(matcher.group(1));
        }
        String s = "";
        for (String find : findBy) {
            s = dict.get(find);
            if (source.contains(find)) {
                source = source.replace("#"+find+"#",s);
            }
        }
        return source;
    }
}
