package com.wfm.servicesystem.config.datasource;

/**
 * description: DataSourceContextHolder
 * date: 2019-12-26 10:00
 *
 * @author: wfm
 * @version: 1.0
 */
public class DataSourceContextHolder {

    private DataSourceContextHolder(){}

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }
}
