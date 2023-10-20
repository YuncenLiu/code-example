package com.liuyuncen;

import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-07-21  16:37
 * @description: TODO
 * @version: 1.0
 */
public class IndexTest {
    private Long id;
    private String name;
    private Date dt;

    public IndexTest() {
    }

    public IndexTest(Long id, String name, Date dt) {
        this.id = id;
        this.name = name;
        this.dt = dt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
}
