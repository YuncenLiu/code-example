package com.kunlun.risk;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.risk
 * @author: Xiang想
 * @createTime: 2024-01-24  16:28
 * @description: TODO
 * @version: 1.0
 */
public class RiskCal {
    private String calCode;
    private String calName;
    private String parentCode;

    public String getCalCode() {
        return calCode;
    }

    public void setCalCode(String calCode) {
        this.calCode = calCode;
    }

    public String getCalName() {
        return calName;
    }

    public void setCalName(String calName) {
        this.calName = calName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
