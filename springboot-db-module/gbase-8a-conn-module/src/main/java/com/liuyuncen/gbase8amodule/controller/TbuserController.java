package com.liuyuncen.gbase8amodule.controller;

import com.liuyuncen.gbase8amodule.entity.Tbuser;
import com.liuyuncen.gbase8amodule.service.TbuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.gbase8amodule.controller
 * @author: Xiang想
 * @createTime: 2024-12-27  10:48
 * @description: TODO
 * @version: 1.0
 */
@Controller
public class TbuserController {

    @Autowired
    private TbuserService tbuserService;

    @GetMapping("/allUser")
    public List<Tbuser> getAllUser(){
        return tbuserService.getAllUsers();
    }
}
