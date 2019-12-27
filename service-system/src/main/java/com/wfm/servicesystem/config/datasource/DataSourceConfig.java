package com.wfm.servicesystem.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 多数据源bean的配置类
 * date: 2019-12-26 10:10
 *
 * @author: wfm
 * @version: 1.0
 */
@Configuration
public class DataSourceConfig {
    @Resource
    private MybatisPlusProperties properties;

    @Bean(name = "dataSourceMaster")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceSlave1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave1")
    public DataSource slave1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 设置动态数据源, 来确定主DataSource
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        // 配置多数据源
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(EnumDataSourceType.MASTER.name(), masterDataSource());
        dataSourceMap.put(EnumDataSourceType.SLAVE1.name(), slave1DataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(PaginationInterceptor paginationInterceptor) {

        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dynamicDataSource());
        // mybatis本身的核心库在springboot打包成jar后有个bug，无法完成别名的扫描
        factory.setVfs(SpringBootVFS.class);

        org.apache.ibatis.session.Configuration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
        }

        factory.setConfiguration(configuration);

        // 分页功能
        factory.setPlugins(new Interceptor[]{ paginationInterceptor});

        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }

        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }

        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }

        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        return factory;
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dynamicDataSource());
//        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml"));
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        sqlSessionFactory.setPlugins(new Interceptor[]{
//                paginationInterceptor() //添加分页功能
//        });
//
//        return sqlSessionFactory.getObject();
//    }

    /**
     * 配置@Transactional注解事务
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
