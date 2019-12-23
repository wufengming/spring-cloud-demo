package com.wfm.servicea.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 配置刷新
@RestController
@RefreshScope
public class TestController {


    @ApolloConfig
    private Config config;

    @Value("${server.port}")
    private String port;

    @Value("${name}")
    private String name;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hello")
    public String hello() {

//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //return "hello world service A 端口：" + port;

//        System.out.println("线程池名称: " + Thread.currentThread().getName());
//        return config.getProperty("name", "default");

        return name;
    }

    @RequestMapping("/otherService")
    public String otherService() {
        return "我是其他服务";
    }

    String fallback(){
        return "服务繁忙";
    }
}
