package com.kunlun.irr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.irr
 * @author: Xiang想
 * @createTime: 2023-10-11  17:38
 * @description: TODO
 * @version: 1.0
 */
public class GetData {

    public static void main(String[] args) throws Exception {
        String filePath = "/Users/xiang/Desktop/IRR.csv";
        GetData getData = new GetData();
        List<String[]> csvData = getData.readCSV(filePath);
        int i = 0;
        List<IRR> irrs = new ArrayList<>();
        List<List<Double>> irrTxt = new ArrayList<>();
        for (String[] csvDatum : csvData) {
            i++;
            if (i == 1) {
                continue;
            }
            IRR irr = new IRR();
            irr.setInsuranceAge(Integer.parseInt(csvDatum[0]));
            irr.setGender(Integer.parseInt(csvDatum[1]));
            irr.setPaymentPeriod(Integer.parseInt(csvDatum[2]));
            irr.setAnnualPremium(Double.parseDouble(csvDatum[3]));
            irr.setPolicyYear(Integer.parseInt(csvDatum[4]));
            irr.setFixedCash(Double.parseDouble(csvDatum[5]));

            CalculateService calculateService = new CalculateService();
            List<Double> irrValue = calculateService.getIrrValue(irr);
            // irrs.add(irr);
            irrTxt.add(irrValue);
        }

        String irrTxtFile = "/Users/xiang/Desktop/irr.txt";
        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(irrTxtFile))) {
            for (List<Double> value : irrTxt) {
                writer.write(value.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
