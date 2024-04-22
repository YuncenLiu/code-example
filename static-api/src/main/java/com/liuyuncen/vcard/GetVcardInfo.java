package com.liuyuncen.vcard;

import ezvcard.Ezvcard;
import ezvcard.VCard;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.vcard
 * @author: Xiang想
 * @createTime: 2024-02-27  14:17
 * @description: TODO
 * @version: 1.0
 */
public class GetVcardInfo {
    public static void main(String[] args) {
        File file = new File("/Users/xiang/Desktop/叶磊和其他123个.vcf"); // 替换成你的 vCard 文件路径

        try {
            List<VCard> vcards = Ezvcard.parse(file).all();
            for (VCard vcard : vcards) {
                System.out.println(vcard.getFormattedName().getValue());
                // 在此处处理其他联系人信息，比如电话号码、电子邮件地址等
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
