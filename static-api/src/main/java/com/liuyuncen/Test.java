package com.liuyuncen;

import lombok.SneakyThrows;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-12  18:08
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        String  sql = "[F1_JG_B1.B45.BQ]=[F1_JG_B1.B7.BQ]+[F1_JG_B1.B8.BQ]+[F1_JG_B1.B9.BQ]+[F1_JG_B1.B10.BQ]+[F1_JG_B1.B11.BQ]+[F1_JG_B1.B12.BQ]+[F1_JG_B1.B13.BQ]+[F1_JG_B1.B14.BQ]+[F1_JG_B1.B15.BQ]+[F1_JG_B1.B16.BQ]+[F1_JG_B1.B17.BQ]+[F1_JG_B1.B18.BQ]+[F1_JG_B1.B19.BQ]+[F1_JG_B1.B20.BQ]+[F1_JG_B1.B21.BQ]+[F1_JG_B1.B22.BQ]+[F1_JG_B1.B23.BQ]+[F1_JG_B1.B24.BQ]+[F1_JG_B1.B25.BQ]+[F1_JG_B1.B26.BQ]+[F1_JG_B1.B27.BQ]+[F1_JG_B1.B28.BQ]+[F1_JG_B1.B29.BQ]+[F1_JG_B1.B30.BQ]+[F1_JG_B1.B31.BQ]+[F1_JG_B1.B32.BQ]+[F1_JG_B1.B33.BQ]";
        String all = sql.replace("[F1_JG_B1.B45.BQ]", "all");
        System.out.println(all);

    }

}
