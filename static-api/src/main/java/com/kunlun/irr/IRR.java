package com.kunlun.irr;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.irr
 * @author: Xiang想
 * @createTime: 2023-10-11  17:34
 * @description: TODO
 * @version: 1.0
 */
public class IRR {
    /**
     * 投保时年龄
     */
    private Integer insuranceAge;
    /**
     * 性别（1-男，2-女）
     */
    private Integer gender;
    /**
     * 交费年期
     */
    private Integer paymentPeriod;
    /**
     * 年交保费（元）
     */
    private Double annualPremium;
    /**
     * 保单年度
     */
    private Integer policyYear;
    /**
     * 固定现金价值（年末）
     */
    private Double fixedCash;
    /**
     * 内部收益率IRR（如果是固定现金价值，则用固定现金价值计算，如果非固定收益，则以预期现金价值计算）
     */
    private double irrValue;

    public IRR() {
    }



    public IRR(Integer insuranceAge, Integer gender, Integer paymentPeriod, Double annualPremium, Integer policyYear, Double fixedCash, double irrValue) {
        this.insuranceAge = insuranceAge;
        this.gender = gender;
        this.paymentPeriod = paymentPeriod;
        this.annualPremium = annualPremium;
        this.policyYear = policyYear;
        this.fixedCash = fixedCash;
        this.irrValue = irrValue;
    }

    /**
     * 投保时年龄
     */
    public Integer getInsuranceAge() {
        return insuranceAge;
    }

    public void setInsuranceAge(Integer insuranceAge) {
        this.insuranceAge = insuranceAge;
    }
    /**
     * 性别（1-男，2-女）
     */
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    /**
     * 交费年期
     */
    public Integer getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(Integer paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }
    /**
     * 年交保费（元）
     */
    public Double getAnnualPremium() {
        return annualPremium;
    }

    public void setAnnualPremium(Double annualPremium) {
        this.annualPremium = annualPremium;
    }
    /**
     * 保单年度
     */
    public Integer getPolicyYear() {
        return policyYear;
    }

    public void setPolicyYear(Integer policyYear) {
        this.policyYear = policyYear;
    }
    /**
     * 固定现金价值（年末）
     */
    public Double getFixedCash() {
        return fixedCash;
    }

    public void setFixedCash(Double fixedCash) {
        this.fixedCash = fixedCash;
    }

    public double getIrrValue() {
        return irrValue;
    }

    public void setIrrValue(double irrValue) {
        this.irrValue = irrValue;
    }



    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // 失败时返回一个空 JSON 对象
        }
    }
}
