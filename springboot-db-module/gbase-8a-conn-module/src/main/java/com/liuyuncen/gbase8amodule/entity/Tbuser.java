package com.liuyuncen.gbase8amodule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.gbase8amodule.demos.entity
 * @author: Xiang想
 * @createTime: 2024-12-27  10:43
 * @description: TODO
 * @version: 1.0
 */
@Entity
public class Tbuser {
    @Id
    private Integer userid;
    private String username;
    private String password;
    private String userroles;
    private String nickname;
}
