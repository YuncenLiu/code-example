package com.liuyuncen.springboot.mock.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.springboot.mock.mode
 * @author: Xiang想
 * @createTime: 2023-10-20  17:09
 * @description: TODO
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String id;
    private String name;
    private String percent;
    private List<Company> parents;
    private List<Company> children;

}
