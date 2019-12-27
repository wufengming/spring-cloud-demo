package com.wfm.servicesystem.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * description:  动态数据源，每执行一次数据库，动态获取数据源
 * AbstractRoutingDataSource 是spring jdbc提供的操作读数据源的抽象类，重写determineCurrentLookupKey() 指定获得当前数据源
 * date: 2019-12-26 09:59
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("------------------当前数据源 {}", DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }
}
