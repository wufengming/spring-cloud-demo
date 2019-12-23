package com.wfm.servicesystem.config.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wfm.servicesystem.common.utils.MyDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;

/**
 * description: MySpringConfig
 * date: 2019-11-06 14:45
 * author: wfm
 * version: 1.0
 */
@Configuration
public class MySpringConfig {
    public MySpringConfig(){
        System.out.println("SpringConfiguration容器启动初始化。。。");
    }

}
