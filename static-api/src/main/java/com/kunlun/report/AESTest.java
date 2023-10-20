package com.kunlun.report;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.report
 * @author: Xiang想
 * @createTime: 2023-10-20  10:37
 * @description: TODO
 * @version: 1.0
 */
public class AESTest {
    private static final String DEFAULT_AES_PASSWORD = "12345@sino#77889";

    public static void main(String[] args) {
        String data = "/Users/xiang/Desktop/data.txt";
        try( BufferedReader bufferedReader = new BufferedReader(new FileReader(data))) {
            String s = bufferedReader.readLine();
            String decrypt = decrypt(s);
            System.out.println("decrypt = " + decrypt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String decrypt(String content) {
        return decrypt(content, DEFAULT_AES_PASSWORD);
    }

    public static String decrypt(String content, String password) {
        byte[] decryptFrom = parseHexStr2Byte(content);
        byte[] decrypt = __decrypt(decryptFrom, password);
        if (decrypt!=null&&decrypt.length!=0){
            return new String(decrypt);
        }
        return null;
    }

    private static byte[] __decrypt(byte[] content, String password) {
        try {
            // 判断Key是否正确
            if (password == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (password.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = password.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            try {
                byte[] original = cipher.doFinal(content);
                return original;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析失败");
        }
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String encrypt(String content, String password){
        byte[] encryptResult = __encrypt(content, password);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        System.out.println("加密后内容:{}"+encryptResultStr);
        return encryptResultStr;
    }

    /**将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] __encrypt(String content, String password) {
        try {
            if (password == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (password.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = password.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] result = cipher.doFinal(content.getBytes("utf-8"));

            return result;

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
