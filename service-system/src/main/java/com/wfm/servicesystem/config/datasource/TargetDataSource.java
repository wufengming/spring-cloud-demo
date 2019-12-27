package com.wfm.servicesystem.config.datasource;



import java.lang.annotation.*;

/**
 * description: 自定义注解，用于类或方法上，优先级：方法>类
 * date: 2019-12-26 10:14
 *
 * @author: wfm
 * @version: 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    EnumDataSourceType value() default EnumDataSourceType.MASTER;
}
