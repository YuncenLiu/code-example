package com.kunlun.ppt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ==>  Preparing: SELECT * FROM D_YWZLGLMBZ WHERE YEARID = ? AND INDEXID = ?
 * ==> Parameters: 2023(String), 1(Integer)
 * <==      Total: 1
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.ppt
 * @author: Xiang想
 * @createTime: 2023-11-13  10:59
 * @description: TODO
 * @version: 1.0
 */
public class TxtSQLAnalyized {

    public static void main(String[] args) {
        String filePath = "/Users/xiang/Desktop/2.log";
        readTxt(filePath);
    }

    public static String readTxt(String filePath){

        StringBuffer stringBuffer = new StringBuffer();
        try {
            // 创建文件输入流
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // 逐行读取并输出文件内容
            String sql = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                String upStr = line.toUpperCase();
                if (upStr.contains("SELECT")) {
                    sql = line.replace("==>  Preparing: ","");
                    System.out.println("-- 原SQL ==> "+sql);
                    sql = sql.replace("?","%s");
                }else if (line.contains("Total")){
                    String[] split = line.split(":");
                    System.out.println("-- 查询数据条数 :"+split[1]);
                }else if (line.contains("Parameters")){
                    String[] split = line.split(":");
                    String parm = split[1];
                    String[] split1 = parm.split(",");
                    List<String> parms = new ArrayList<>();
                    for (String s : split1) {
                        if (s.contains("Integer")) {
                            parms.add(s.replace("(Integer)","").trim());
                        } else if (s.contains("String")) {
                            parms.add("'" + s.replace("(String)","").trim() + "'");
                        } else {
                            throw new RuntimeException("异常了");
                        }
                    }
                    switch (parms.size()) {
                        case  1:
                            sql = String.format(sql,parms.get(0));
                            break;
                        case  2:
                            sql = String.format(sql,parms.get(0),parms.get(1));
                            break;
                        case  3:
                            sql = String.format(sql,parms.get(0),parms.get(1),parms.get(2));
                            break;
                        case  4:
                            sql = String.format(sql,parms.get(0),parms.get(1),parms.get(2),parms.get(3));
                            break;
                        case  5:
                            sql = String.format(sql,parms.get(0),parms.get(1),parms.get(2),parms.get(3),parms.get(4));
                            break;
                        case  6:
                            sql = String.format(sql,parms.get(0),parms.get(1),parms.get(2),parms.get(3),parms.get(4),parms.get(5));
                            break;
                        case  7:
                            sql = String.format(sql,parms.get(0),parms.get(1),parms.get(2),parms.get(3),parms.get(4),parms.get(5),parms.get(6));
                            break;
                        case  8:
                            sql = String.format(sql,parms.get(0),parms.get(1),parms.get(2),parms.get(3),parms.get(4),parms.get(5),parms.get(6),parms.get(7));
                            break;
                        default:
                            System.out.println(parms.size());
                            System.out.println(sql);
                            throw new RuntimeException("参数放不下");
                    }

                    System.out.println(sql+";");
                    System.out.println("-- 参数 "+ split1.length+" 个，分别是："+parm);

                } else if (line.contains("==>")){
                    String s = line.replace("==>","");
                    System.out.println();
                    System.out.println("-- =============================================================");
                    System.out.println("-- 当前 PPT 页数：" + s);
                    System.out.println("-- =============================================================");
                } else{
                    System.out.println(line);
                }
            }

            // 关闭文件流
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

}
