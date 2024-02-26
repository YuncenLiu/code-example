package com.kunlun.risk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        String index1Path = "/Users/xiang/Desktop/index.txt";

        List<RiskIndex> riskIndexList = test.readRiskIndex(markFilePath);
        List<RiskCal> riskCalList = test.readRiskCal(index1Path);


        System.out.println("riskIndexList = "+ riskIndexList.size());
        System.out.println("riskCalList = " + riskCalList.size());


        List<RiskIndex> result = new ArrayList<>();
        for (RiskIndex riskIndex : riskIndexList) {
            String calFormula = riskIndex.getCalFormula();
            Pattern pattern = Pattern.compile("#(.*?)#");
            Matcher matcher = pattern.matcher(calFormula);
            List<String> findBy = new ArrayList<>();
            while (matcher.find()) {
                findBy.add(matcher.group(1));
            }
            String s = riskIndex.getCalFormula();
            for (String find : findBy) {
                for (RiskCal riskCal : riskCalList) {
                    if (riskCal.getCalCode().equals(find) && riskCal.getParentCode().equals(riskIndex.getIndexCode())) {
                        s = s.replace("#"+find+"#",riskCal.getCalName());
                    }
                }
            }
            riskIndex.setCalFormulaStr(s);
            result.add(riskIndex);
        }

        System.out.println("result = "+result.size());


        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        for (RiskIndex riskIndex : result) {
            System.out.println(riskIndex.getIndexCode() + "," + riskIndex.getIndexName() + ","+ riskIndex.getCalFormula() + "," + riskIndex.getCalFormulaStr());
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


    public List<RiskIndex> readRiskIndex(String filePath) {
        List<RiskIndex> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                RiskIndex riskIndex = new RiskIndex();
                riskIndex.setIndexCode(split[0]);
                riskIndex.setIndexName(split[1]);
                riskIndex.setCalFormula(split[2]);
                data.add(riskIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<RiskCal> readRiskCal(String filePath) {
        List<RiskCal> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                RiskCal riskCal = new RiskCal();
                riskCal.setCalCode(split[0]);
                riskCal.setCalName(split[1]);
                riskCal.setParentCode(split[2]);
                data.add(riskCal);
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
