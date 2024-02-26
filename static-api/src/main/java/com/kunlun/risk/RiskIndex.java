package com.kunlun.risk;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.risk
 * @author: Xiang想
 * @createTime: 2024-01-24  16:28
 * @description: TODO
 * @version: 1.0
 */
public class RiskIndex {
    private String indexCode;
    private String indexName;
    private String calFormula;

    private String calFormulaStr;

    public String getCalFormulaStr() {
        return calFormulaStr;
    }

    public void setCalFormulaStr(String calFormulaStr) {
        this.calFormulaStr = calFormulaStr;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getCalFormula() {
        return calFormula;
    }

    public void setCalFormula(String calFormula) {
        this.calFormula = calFormula;
    }
}
