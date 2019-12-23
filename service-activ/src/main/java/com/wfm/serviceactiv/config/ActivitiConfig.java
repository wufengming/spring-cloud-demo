package com.wfm.serviceactiv.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * description: ActivitiConfig
 * date: 2019-10-28 15:57
 * author: wfm
 * version: 1.0
 */
@Component
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");

        //是否使用activiti自带用户组织表
        springProcessEngineConfiguration.setDbIdentityUsed(true);
        //启动的时候是否去创建表，如果第一次启动这里必须设置为true
        springProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
    }
}
