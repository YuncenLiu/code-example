package com.liuyuncen.file;

import com.liuyuncen.core.tool.utils.UrlUtil;

import java.io.File;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.file
 * @author: Xiang想
 * @createTime: 2025-02-08  16:00
 * @description: TODO
 * @version: 1.0
 */
public class DownloadFile {
    public static void main(String[] args) {
        String fileUrl = "http://127.0.0.1:9000/exchange-module/upload/20250208/3a758d2697f35ea895e70502dd3d26cb.zip";
        String fileName = "3a758d2697f35ea895e70502dd3d26cb.zip"; // 下载文件的文件名

        UrlUtil.downloadFile(fileUrl,"/Users/xiang/Desktop/cloud/tmp" + File.separator + fileName );
        System.out.println( File.separator + fileName);
    }

}
