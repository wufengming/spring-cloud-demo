package com.wfm.servicesystem.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * description: 创建aop切面
 * date: 2019-12-26 10:15
 *
 * @author: wfm
 * @version: 1.0
 */
@Component
@Aspect
@Slf4j
// @Order(1) 默认优先级 最后执行 可调整Order
public class DynamicDataSourceAspect {

    /**
     * 切入点@TargetDataSource方法生效
     * @param point
     * @param targetDataSource
     */
    @Before("@annotation(targetDataSource)")
    public void before(JoinPoint point, TargetDataSource targetDataSource) {
        try {
            TargetDataSource annotationOfClass = point.getTarget().getClass().getAnnotation(TargetDataSource.class);
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);
            TargetDataSource methodAnnotation = method.getAnnotation(TargetDataSource.class);
            methodAnnotation = methodAnnotation == null ? annotationOfClass : methodAnnotation;
            EnumDataSourceType dataSourceType = methodAnnotation != null && methodAnnotation.value() != null ? methodAnnotation.value() : EnumDataSourceType.MASTER;
            DataSourceContextHolder.setDataSource(dataSourceType.name());
        } catch (NoSuchMethodException e) {
            log.warn("Aspect targetDataSource inspect exception.", e);
        }
    }

    /**
     * 切点执行完后 切换成主数据库
     * @param point
     * @param targetDataSource
     */
    @After("@annotation(targetDataSource)")
    public void after(JoinPoint point, TargetDataSource targetDataSource) {
        DataSourceContextHolder.clearDataSource();
    }
}
