package com.kunlun.vpnItsm;

import lombok.Data;

/**
 * @belongsProject: Xiang-Cloud
 * @belongsPackage: com.liuyuncen.itsmRequestVpn.entity
 * @author: Xiang想
 * @createTime: 2024-11-11  09:55
 * @description: TODO
 * @version: 1.0
 */
@Data
public class VpnResponseEntity {

    // 404 请求地址错误
    // 1 请求地址的中的 action 或者 controller 不存在
    // 4 sinfor_apitoken 接口认证错误
    // -2 参数错误
    // -10 用户不存在
    private String code;
    private String success;
    private String result;
    private String message;
    private String readOnlyInfo;
}
