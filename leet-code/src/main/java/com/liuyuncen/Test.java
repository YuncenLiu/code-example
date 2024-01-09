package com.liuyuncen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-01-09  09:36
 * @description: TODO
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        String s = "leetscode";
        String[] dictionary = new String[]{"leet","code","leetcode"};
        int i = minExtraChar(s, dictionary);
        System.out.println("i = " + i);


    }

    public static int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : dictionary) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        d[0] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (map.containsKey(s.substring(j, i))) {
                    d[i] = Math.min(d[i], d[j]);
                }
            }
        }
        return d[n];

    }
}
