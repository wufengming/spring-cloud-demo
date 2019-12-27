package com.wfm.servicesystem.config.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * description: 操作数据源
 * date: 2019-12-26 10:00
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
public class DataSourceContextHolder {

    private DataSourceContextHolder(){}

    /**
     * 存放当前线程使用的数据源类型
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbType
     */
    public static void setDataSource(String dbType){
        log.info("-------- 设置数据源数据源为 ：{} ", dbType);
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        contextHolder.remove();
    }
}
