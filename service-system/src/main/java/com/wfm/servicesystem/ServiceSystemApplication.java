package com.wfm.servicesystem;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableConfigurationProperties
@MapperScan({"com.wfm.servicesystem.mapper"})
@ServletComponentScan
@SpringBootApplication(scanBasePackages="com.wfm")
public class ServiceSystemApplication {

    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext application =SpringApplication.run(ServiceSystemApplication.class, args);
        // 打印项目信息
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n-----------------------------------------------------\n\t" +
                "Application Spring-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
                "swagger-ui: \thttp://" + ip + ":" + port + path + "swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "doc.html\n" +
                "----------------------------------------------------------");
    }

}
