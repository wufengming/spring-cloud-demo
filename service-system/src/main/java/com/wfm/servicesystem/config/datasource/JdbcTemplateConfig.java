package com.wfm.servicesystem.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * description: JdbcTemplateConfig
 * date: 2019-12-25 13:12
 *
 * @author: wfm
 * @version: 1.0
 */
@Configuration
public class JdbcTemplateConfig {

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.one")
//    DataSource dsOne() {
//        return DruidDataSourceBuilder.create().build();
//    }
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.two")
//    DataSource dsTwo() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne") DataSource dsOne) {
//        return new JdbcTemplate(dsOne);
//    }
//
//    @Bean
//    JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo") DataSource dsTwo) {
//        return new JdbcTemplate(dsTwo);
//    }

}
