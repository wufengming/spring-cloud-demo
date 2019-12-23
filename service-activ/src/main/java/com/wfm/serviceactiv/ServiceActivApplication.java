package com.wfm.serviceactiv;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Activiti5.23需要排除Security
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.wfm.serviceactiv.mapper")
@ComponentScan({"com.wfm"})
public class ServiceActivApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActivApplication.class, args);
    }

}
