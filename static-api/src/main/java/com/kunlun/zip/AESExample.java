package com.kunlun.zip;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.zip
 * @author: Xiang想
 * @createTime: 2024-12-31  17:52
 * @description: TODO
 * @version: 1.0
 */
public class AESExample {
    // AES 加密方法（CBC模式）
    public static String encrypt(String data, String key, String iv) throws Exception {
        // 创建 AES 密钥
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // 创建偏移量（IV）
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

        // 创建加密器（AES CBC 模式）
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 初始化加密器
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // 执行加密操作
        byte[] encryptedData = cipher.doFinal(data.getBytes());

        // 返回加密后的数据（Base64 编码）
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // AES 解密方法（CBC模式）
    public static String decrypt(String encryptedData, String key, String iv) throws Exception {
        // 创建 AES 密钥
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // 创建偏移量（IV）
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

        // 创建解密器（AES CBC 模式）
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 初始化解密器
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // 执行解密操作
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedBytes);

        // 返回解密后的数据
        return new String(decryptedData);
    }

    public static void main(String[] args) {
        try {
            // 给定的 AES 密钥和偏移量（IV）




            String key = "meixin1234567899"; // 16 字节 AES 密钥
            String iv = "1234567812345678"; // 16 字节 IV

            key = "meixin@202501029"; // 16 字节 AES 密钥
            iv = "1234567812345678"; // 16 字节 IV
            // Encrypted: PHGJ+kL0/9X7KTvA2WPaduTsKwc48FQDCnY3AFtEF29TNBgSmnSaQiB3qvESM6tXuuPfR6w4L1B5HEw6ZlRgVY+VRhf6c3Jytvo9gt/oWtug3THDiYGrTzTG90yN+Gbbr5gd++xhIvF8TIBva1GI5dCJ491vpbvNePz2XPPeyiA=
            // Encrypted: svdobKvZ89JHBEheqpjcFm80qCw8i3VdA17OZOvxKGiedel4WTMiHt4C3WDjEKS1uDVTXFcBck8lOrw2zOdb39Rx6yllJWBHlWFhmuHjV5hSuN/Du09oFw0j7SmJaZLirw4Zo21sdiCVjqa4VCQ67DK3DJJodNDi27wKY8SGsRo=
            // 要加密的明文数据
//            String plainText = "\"reqMsg \": {\n" +
//                    "    \"timestamp \": \"2024-12-23 10:25:52\",\n" +
//                    "    \"orgCode\": \"dev_s_meixin_sftp\",\n" +
//                    "    \"dataTypeCode\": \"SP\"\n" +
//                    "  }";
//
//            String message = "{\n" +
//                    "    \"chnlCode\": \"meixin\",\n" +
//                    "    \"serviceCode\": \"getSFTPpassword\",\n" +
//                    "    \"timestamp\": \"20241219161808\",\n" +
//                    "    \"reqMsg\": {\n" +
//                    "        \"timestamp\": \"2024-12-23 10:25:52\",\n" +
//                    "        \"orgCode\": \"dev_s_meixin_sftp\",\n" +
//                    "        \"dataTypeCode\": \"SP\"\n" +
//                    "    }\n" +
//                    "}";

//            JSONObject jsonObject = JSON.parseObject(message);
//            String reqMsg = jsonObject.getString("reqMsg");

//            String plainText = "KL,HelloWorld";

            String plainText = "z0GCfdbrlPeR8HpQzwDA1cytu9+fwAa93fxOLqgvaPUEbLQRbMoDe/BPS1R9GLQBbHsUBJF8LCvsVbqKbdvmjS60+tCZb8AMkJa8hSvdJ0g=";

            // 加密
            String encryptedText = decrypt(plainText, key, iv);
            System.out.println("Encrypted: " + encryptedText);

            // 解密
//            String decryptedText = decrypt(encryptedText, key, iv);
//            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
