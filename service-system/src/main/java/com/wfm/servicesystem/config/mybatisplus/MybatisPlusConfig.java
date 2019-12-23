package com.wfm.servicesystem.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * description: MybatisPlusConfig
 * date: 2019-11-06 13:29
 * author: wfm
 * version: 1.0
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * mybatios-plus乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {

        return new OptimisticLockerInterceptor();
    }

    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        //key的值是对应的数据库的productName，value则是一个自定义的值
        Properties p = new Properties();
        //mysql
        p.setProperty("MySQL", "mysql");
        //oracle
        p.setProperty("Oracle", "oracle");
        //达梦7
        p.setProperty("DM DBMS", "dm7");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }
}
