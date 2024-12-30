package com.liuyuncen.gbase8amodule.repository;

import com.liuyuncen.gbase8amodule.entity.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.gbase8amodule.demos.repository
 * @author: Xiang想
 * @createTime: 2024-12-27  10:45
 * @description: TODO
 * @version: 1.0
 */
public interface TbuserRepository extends JpaRepository<Tbuser, Integer> {
    List<Tbuser> queryAllByUserid(Integer userid);
}
