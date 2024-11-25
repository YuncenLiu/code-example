//package com.liuyuncen.config;
//
//import com.liuyuncen.demo.HelloWorldService;
//import com.liuyuncen.demo.HelloWorldServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ws.config.annotation.EnableWs;
//import org.springframework.ws.config.annotation.WsConfigurerAdapter;
//import org.springframework.ws.server.EndpointInterceptor;
//import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
//
//import java.util.List;
//
///**
// * @belongsProject: 测试平台
// * @belongsPackage: com.liuyuncen.config
// * @author: Xiang想
// * @createTime: 2024-11-20  14:03
// * @description: TODO
// * @version: 1.0
// */
//@Configuration
//@EnableWs
//public class HelloWorldServiceConfig extends WsConfigurerAdapter {
//
//    @Bean
//    public HelloWorldService helloWorldService(){
//        return new HelloWorldServiceImpl();
//    }
//
//    @Bean
//    public void addInterceptors(List<EndpointInterceptor> interceptorList){
//        interceptorList.add(new PayloadLoggingInterceptor());
//    }
//}
