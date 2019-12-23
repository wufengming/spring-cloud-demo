package com.wfm.serviceb.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.wfm.serviceb.ServiceClient.ServiceAFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ServiceAFeignClient serverAFeignClient;

    @ApolloConfig
    private Config config;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world service B";
    }


    @GetMapping("/apollo")
    public String apollo() {
        System.out.println("线程池名称: " + Thread.currentThread().getName());
        return config.getProperty("name", "default");
    }

    @RequestMapping("/call")
    public String call() {
        String result = serverAFeignClient.hello();
        return "b to a 访问结果 ----- " + result;
    }
}
