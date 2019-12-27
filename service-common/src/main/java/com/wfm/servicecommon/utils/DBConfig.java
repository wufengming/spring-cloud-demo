package com.wfm.servicecommon.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * description: DBConfig
 * date: 2019-12-27 14:52
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
@Data
public class DBConfig {
    private String driverClassName;
    private String userName;
    private String password;
    private String url;

    /**
     * 默认数据库的配置读取 db1
     */
    public DBConfig(){
        Properties config = YmlUtil.getYmlByFileName("config/jdbc.yml");

        this.driverClassName = config.getProperty("jdbc.db1.driverClassName");
        this.userName = config.getProperty("jdbc.db1.userName");
        this.password = config.getProperty("jdbc.db1.password");
        this.url = config.getProperty("jdbc.db1.url");
    }

    /**
     * 读取指定的数据库的配置信息
     * @param db
     */
    public DBConfig(String db) {
        Properties config = YmlUtil.getYmlByFileName("config/jdbc.yml");

        // 默认
        if(StringUtils.isEmpty(db)) {
            db="db1";
        }

        this.driverClassName = config.getProperty(String.format("jdbc.%s.driverClassName",db));
        this.userName = config.getProperty(String.format("jdbc.%s.userName",db));
        this.password = config.getProperty(String.format("jdbc.%s.password",db));
        this.url = config.getProperty(String.format("jdbc.%s.url",db));
    }

    public DBConfig(String driverClassName, String userName, String password, String url) {
        super();
        this.driverClassName = driverClassName;
        this.userName = userName;
        this.password = password;
        this.url = url;
    }
}
