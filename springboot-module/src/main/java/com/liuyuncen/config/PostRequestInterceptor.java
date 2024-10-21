package com.liuyuncen.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2024-10-17  13:57
 * @description: TODO
 * @version: 1.0
 */
@Component
public class PostRequestInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("====> 现在访问到资源是： " + request.getRequestURI() ) ;
        // 检查响应状态码
        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            // 打印 404 错误信息
            System.out.println("无法访问到资源：" + request.getRequestURI() + "  现在重定向到 404 页面");
            response.sendRedirect(request.getContextPath() + "/404.html");
        }
    }
}
