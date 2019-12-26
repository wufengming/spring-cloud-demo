package com.wfm.servicesystem.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * description: DynamicDataSource
 * date: 2019-12-26 09:59
 *
 * @author: wfm
 * @version: 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
