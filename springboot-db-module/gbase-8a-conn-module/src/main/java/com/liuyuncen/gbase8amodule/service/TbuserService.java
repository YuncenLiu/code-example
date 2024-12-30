package com.liuyuncen.gbase8amodule.service;

import com.liuyuncen.gbase8amodule.entity.Tbuser;
import com.liuyuncen.gbase8amodule.repository.TbuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.gbase8amodule
 * @author: Xiang想
 * @createTime: 2024-12-27  10:47
 * @description: TODO
 * @version: 1.0
 */
@Service
public class TbuserService {
    @Autowired
    private TbuserRepository tbuserRepository;

    public List<Tbuser> getAllUsers(){
        return tbuserRepository.findAll();
    }

}
