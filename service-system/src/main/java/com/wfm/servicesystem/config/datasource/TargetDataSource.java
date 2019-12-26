package com.wfm.servicesystem.config.datasource;



import java.lang.annotation.*;

/**
 * description: 切换数据源 注解
 * date: 2019-12-26 10:14
 *
 * @author: wfm
 * @version: 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    EnumDataSourceType value() default EnumDataSourceType.MYSQL;
}
