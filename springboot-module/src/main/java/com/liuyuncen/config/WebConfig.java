package com.liuyuncen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2024-10-17  13:58
 * @description: TODO
 * @version: 1.0
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PostRequestInterceptor postRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(postRequestInterceptor)
                .addPathPatterns("/**"); // 拦截所有路径
    }
}
