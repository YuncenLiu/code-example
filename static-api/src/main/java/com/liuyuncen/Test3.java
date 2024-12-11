package com.liuyuncen;

import java.util.Arrays;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-12-05  11:06
 * @description: TODO
 * @version: 1.0
 */
public class Test3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("名称",
                "公网IP",
                "内网IP",
                "接入方式",
                "Agent状态",
                "awnx1-cdata-pnode01",
                "--",
                "10.129.24.101",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode02",
                "--",
                "10.129.24.110",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode03",
                "--",
                "10.129.24.44",
                "ec2-user",
                "（本地工具）",
                "未安装",
                "awnx1-cdata-pnode04",
                "--",
                "10.129.24.123",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode05",
                "--",
                "10.129.24.98",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode06",
                "--",
                "10.129.24.122",
                "ec2-user",
                "（本地工具）",
                "未安装",
                "awnx1-cdata-pnode07",
                "--",
                "10.129.24.82",
                "ec2-user",
                "（本地工具）",
                "未安装",
                "awnx1-cdata-pnode08",
                "--",
                "10.129.24.36",
                "ec2-user",
                "（本地工具）",
                "未安装",
                "awnx1-cdata-pnode09",
                "--",
                "10.129.24.125",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode10",
                "--",
                "10.129.24.75",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode11",
                "--",
                "10.129.24.69",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-cdata-pnode12",
                "--",
                "10.129.24.91",
                "ec2-user",
                "（本地工具）",
                "未安装",
                "awnx1-crisk-pnode01",
                "--",
                "10.129.24.8",
                "ec2-user",
                "（本地工具）",
                "未安装",
                "awnx1-crisk-pnode02",
                "--",
                "10.129.24.84",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "awnx1-crisk-pnode03",
                "--",
                "10.129.24.16",
                "ec2-user",
                "（Web桌面）",
                "未安装",
                "aws运维主机",
                "--",
                "10.5.128.42",
                "liyan",
                "（本地工具）",
                "未安装",
                "进入批量管理");

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);

            if (str.contains("10.129.")) {
                System.out.println(list.get(i-2) + "," + list.get(i));
            }
        }

    }
}
