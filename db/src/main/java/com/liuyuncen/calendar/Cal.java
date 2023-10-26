package com.liuyuncen.calendar;

import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.calendar
 * @author: Xiang想
 * @createTime: 2023-10-26  14:36
 * @description: TODO
 * @version: 1.0
 */
public class Cal {
    private Integer rowId;
    private String title;
    private String summary;
    private Date startDate;
    private Date endDate;
    private Date modifyDate;
    private String describe;
    private String url;


    public Cal() {
    }

    public Cal(Integer rowId, String title, String summary, Date startDate, Date endDate, Date modifyDate, String describe, String url) {
        this.rowId = rowId;
        this.title = title;
        this.summary = summary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.modifyDate = modifyDate;
        this.describe = describe;
        this.url = url;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Cal{" +
                "rowId=" + rowId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", modifyDate=" + modifyDate +
                ", describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
