package com.liuyuncen;

import java.security.SecureRandom;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2024-12-26  15:37
 * @description: TODO
 * @version: 1.0
 */
public class RandomPassword {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(generatePassword(12));
        }
    }

    public static String generatePassword(int length) {
        // 定义各个字符集
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = "!@#$^*()_+-=.,;:";
        String specialStartEndChars = "!.,:;"; // 特殊符号，不允许出现在开头或结尾

        // 使用 SecureRandom 来确保密码的随机性
        SecureRandom random = new SecureRandom();

        // 确保每个类别的字符至少包含一个
        StringBuilder password = new StringBuilder();

        // 必须至少包含一个小写字母、大写字母、数字、特殊符号
        password.append(lowercase.charAt(random.nextInt(lowercase.length())));
        password.append(uppercase.charAt(random.nextInt(uppercase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));

        // 选择一个特殊符号，确保它不在开始或末尾
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // 填充剩余部分，确保密码总长度为 12
        String allChars = lowercase + uppercase + digits + specialChars;
        for (int i = password.length(); i < length - 1; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // 生成一个特殊符号，并确保它不出现在开头或末尾
        String finalPassword = password.toString();
        char[] passwordArray = finalPassword.toCharArray();

        // 随机交换密码中的字符
        for (int i = 0; i < passwordArray.length; i++) {
            int j = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        // 确保特殊符号不出现在开头或末尾
        while (isSpecialCharAtStartOrEnd(passwordArray)) {
            // 如果特殊符号出现在开头或末尾，重新打乱密码
            for (int i = 0; i < passwordArray.length; i++) {
                int j = random.nextInt(passwordArray.length);
                char temp = passwordArray[i];
                passwordArray[i] = passwordArray[j];
                passwordArray[j] = temp;
            }
        }

        // 返回最终密码
        return new String(passwordArray);
    }

    // 判断密码的开头或末尾是否是特殊符号
    private static boolean isSpecialCharAtStartOrEnd(char[] passwordArray) {
        String specialStartEndChars = "!.,:;";
        return specialStartEndChars.indexOf(passwordArray[0]) >= 0 || specialStartEndChars.indexOf(passwordArray[passwordArray.length - 1]) >= 0;
    }
}
