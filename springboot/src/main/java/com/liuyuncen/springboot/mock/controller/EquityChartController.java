package com.liuyuncen.springboot.mock.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.springboot.mock.controller
 * @author: Xiang想
 * @createTime: 2023-10-20  16:59
 * @description: TODO
 * @version: 1.0
 */
@RestController
@RequestMapping("/equity-chart")
public class EquityChartController {

    @RequestMapping("/get-data")
    @CrossOrigin(origins = "http://localhost:63343") // 允许来自localhost:3000的跨域请求
    public ResponseEntity<Object> getData(){
        // 创建请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Your-Custom-Value");

        // 创建JSON响应
        Object responseData = new Object() {
            public String id = "abc1005";
            public String name = "山东吠舍科技有限责任公司";
        };

        return ResponseEntity.ok().headers(headers).body(responseData);
    }
//
//    private Object createCompany() {
//        // 创建包含请求数据的Java对象
//        // 在这里，你可以构建与你提供的JSON数据结构相匹配的Java对象
//
//        // 例如：
//        Company data = new Company();
//        data.setId("abc1005");
//        data.setName("山东吠舍科技有限责任公司");
//
//        // 创建子公司数据
//        // 你可以根据需要构建子公司层次结构
//        // 这里只是示例，你可以逐层创建子公司
//
//        Company childCompany1 = new Company();
//        childCompany1.setId("abc1006");
//        childCompany1.setName("山东第一首陀罗科技服务有限公司");
//        childCompany1.setPercent("100%");
//
//        Company childCompany2 = new Company();
//        childCompany2.setId("abc1007");
//        childCompany2.setName("山东第二首陀罗程技术有限公司");
//        childCompany2.setPercent("100%");
//
//       List<Company> list = new ArrayList<Company>();
//       list.add(childCompany1);
//       list.add(childCompany2);
//       data.setChildren(list);
//
//        // 添加更多的子公司和层次结构根据需要
//
//        // 返回创建的数据
//        return data;
//    }
}
