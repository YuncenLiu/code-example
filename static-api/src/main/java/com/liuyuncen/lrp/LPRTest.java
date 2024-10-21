package com.liuyuncen.lrp;

import java.util.Scanner;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.lrp
 * @author: Xiang想
 * @createTime: 2024-10-21  17:45
 * @description: TODO
 * @version: 1.0
 */
public class LPRTest {

    public static void main(String[] args) {
        loopCa();
    }

    public static void loopCa(){
        Scanner scanner = new Scanner(System.in);

        // 输入利率（以百分比形式输入，例如4.2表示4.2%）
        System.out.print("请输入年利率（%）：");
        double annualInterestRate = scanner.nextDouble() / 100; // 转换为小数

        // 输入贷款本金
        System.out.print("请输入贷款本金（元）：");
        double principal = scanner.nextDouble();

        // 输入贷款月数
        System.out.print("请输入贷款月数：");
        int months = scanner.nextInt();

        // 计算每月还款额和总还款额
        double[] repaymentResults = calculateRepayment(annualInterestRate, principal, months);
        double monthlyRepayment = repaymentResults[0];
        double totalRepayment = repaymentResults[1];

        // 输出结果
        System.out.printf("每月还款额：%.2f 元%n", monthlyRepayment);
        System.out.printf("还款总额：%.2f 元%n", totalRepayment);

        // 打印每月还款明细
        printRepaymentDetails(principal, monthlyRepayment, annualInterestRate, months);

        scanner.close();
    }
    public static double[] calculateRepayment(double annualRate, double principal, int months) {
        // 将年利率转为月利率
        double monthlyRate = annualRate / 12;

        // 计算每月还款额
        double monthlyRepayment = (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        // 计算总还款额
        double totalRepayment = monthlyRepayment * months;

        return new double[]{monthlyRepayment, totalRepayment};
    }

    public static void printRepaymentDetails(double principal, double monthlyRepayment, double annualRate, int months) {
        double monthlyRate = annualRate / 12; // 月利率
        double remainingPrincipal = principal; // 剩余本金

        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", "期次", "偿还本息", "偿还利息", "偿还本金", "剩余本金");

        for (int month = 1; month <= months; month++) {
            double interestPayment = remainingPrincipal * monthlyRate; // 偿还利息
            double principalPayment = monthlyRepayment - interestPayment; // 偿还本金
            remainingPrincipal -= principalPayment; // 更新剩余本金

            // 打印当前期次的还款明细
            System.out.printf("%-10d %-15.2f %-15.2f %-15.2f %-15.2f%n", month, monthlyRepayment, interestPayment, principalPayment, remainingPrincipal);
        }
    }
}
