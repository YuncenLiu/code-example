package com.liuyuncen;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-12  18:08
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        /*

       LEFT JOIN (
          SELECT
            T10.SUBJECT_AREA,
            T10.WORKFLOW_NAME,
            T10.SESSION_NAME,
            T10.START_TIME,
            T10.END_TIME,
            TO_CHAR(T10.WKF_START_TIME,
            'HH24MI') TIME_IN_24HOUR_FORMAT,
            (END_TIME - START_TIME)*24*60*60 TIME_BETWEEN
          FROM
            DATAMART.D_WKF_MONITOR T10
          WHERE
            START_TIME >= TO_DATE('2024-09-30', 'yyyy-mm-dd')
            AND START_TIME < TO_DATE('2024-10-01', 'yyyy-mm-dd')
            AND WORK_TYPE = 'Informatica_Ha'
        )TT10
        ON TT1.SUBJECT_AREA = TT10.SUBJECT_AREA
        AND TT1.WORKFLOW_NAME = TT10.WORKFLOW_NAME
        AND TT1.SESSION_NAME = TT10.SESSION_NAME
        AND TT1.TIME_IN_24HOUR_FORMAT = TT10.TIME_IN_24HOUR_FORMAT

        * */

        int j = 11;
        for (int i = 1; i < 8; i++,j++) {
            String iStr = String.valueOf(i);
            String iStrAdd = String.valueOf(i+1);
            if (iStr.length() == 1){
                iStr = "0" + iStr;
            }

            if (iStrAdd.length() == 1){
                iStrAdd = "0" + iStrAdd;
            }



            String sql = "LEFT JOIN (\n" +
                    "          SELECT\n" +
                    "            T"+j+".SUBJECT_AREA,\n" +
                    "            T"+j+".WORKFLOW_NAME,\n" +
                    "            T"+j+".SESSION_NAME,\n" +
                    "            T"+j+".START_TIME,\n" +
                    "            T"+j+".END_TIME,\n" +
                    "            TO_CHAR(T"+j+".WKF_START_TIME,\n" +
                    "            'HH24MI') TIME_IN_24HOUR_FORMAT,\n" +
                    "            (END_TIME - START_TIME)*24*60*60 TIME_BETWEEN\n" +
                    "          FROM\n" +
                    "            DATAMART.D_WKF_MONITOR T"+j+"\n" +
                    "          WHERE\n" +
                    "            START_TIME >= TO_DATE('2024-10-"+iStr+"', 'yyyy-mm-dd')\n" +
                    "            AND START_TIME < TO_DATE('2024-10-"+iStrAdd+"', 'yyyy-mm-dd')\n" +
                    "            AND WORK_TYPE = 'Informatica_Ha'\n" +
                    "        )TT"+j+"\n" +
                    "        ON TT1.SUBJECT_AREA = TT"+j+".SUBJECT_AREA\n" +
                    "        AND TT1.WORKFLOW_NAME = TT"+j+".WORKFLOW_NAME\n" +
                    "        AND TT1.SESSION_NAME = TT"+j+".SESSION_NAME\n" +
                    "        AND TT1.TIME_IN_24HOUR_FORMAT = TT"+j+".TIME_IN_24HOUR_FORMAT";

//            System.out.println(sql);


        }

//
//        for (int i = 1; i < 31; i++) {
//            String iStr = String.valueOf(i);
//            if (iStr.length() == 1) {
//                iStr = "0" + i;
//            }
//               /*
//            SUBTRACT_22_15 NUMBER(11,0), -- 22 减 15 执行时间
//             RATIO_22_15 NUMBER(11,2), -- 22 减 15 优化比例
//             RATIO_LEVEL_22_15 VARCHAR(20), -- 22 减 15 优化等级
//            * */
//
//            System.out.println("SUBTRACT_"+iStr+"_15 NUMBER(11,0),");
//            System.out.println("RATIO_"+iStr+"_15 NUMBER(11,2),");
//            System.out.println("RATIO_LEVEL_"+iStr+"_15 VARCHAR(20),");
//        }




        for (int s = 1; s < 8; s++) {
            String i = String.valueOf(s);
            if (i.length() == 1) {
                i = "0"+i;
            }

            String sql = "UPDATE DATAMART_PRE.TMP_INFO_EXECUTE_TIME_ANALYSIS\n" +
                    "SET RATIO_LEVEL_"+i+"_15 =\n" +
                    "CASE\n" +
                    "WHEN RATIO_"+i+"_15 < -3 THEN '<=300%' \n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -3 AND -2.01 THEN '-300%<=&<=-201%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -2 AND -1.01 THEN '-200%<=&<=-101%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -1 AND -0.81 THEN '-100%<=&<=-81%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -0.8 AND -0.51 THEN '-80%<=&<=-51%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -0.5 AND -0.31 THEN '-50%<=&<=-31%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -0.3 AND -0.21 THEN '-30%<=&<=-21%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -0.2 AND -0.11 THEN '-20%<=&<=-11%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN -0.1 AND -0.01 THEN '-10%<=&<=-1%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0 AND 0 THEN '0%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0.01 AND 0.10 THEN '1%<=&<10%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0.11 AND 0.20 THEN '11%<=&<=20%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0.21 AND 0.30 THEN '21%<=&<=30%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0.31 AND 0.50 THEN '31%<=&<=50%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0.51 AND 0.80 THEN '51%<=&<=80%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 0.81 AND 1 THEN '81%<=&<=100%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 1.01 AND 2 THEN '101%<=&<=200%'\n" +
                    "WHEN RATIO_"+i+"_15 BETWEEN 2.01 AND 3 THEN '201%<=&<=300%'\n" +
                    "WHEN RATIO_"+i+"_15  >3 THEN '>=300%'\n" +
                    "ELSE '' -- 保持原值，如果需要\n" +
                    "END;";

            System.out.println(sql);
        }
    }
}
