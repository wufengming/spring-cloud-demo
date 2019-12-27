package com.wfm.servicecommon.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * description: yml文件工具类
 * date: 2019-12-27 13:09
 *
 * @author: wfm
 * @version: 1.0
 */
public class YmlUtil {

    /**
     * 根据文件名获取yml的文件内容
     * @return
     */
    public static Properties getYmlByFileName(String file)  {

        Resource resource = new ClassPathResource(file);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return properties;
    }

    /**
     * 根据key获取值
     * @param key
     * @param file
     * @return
     */
    public static String getValue(String key,String file) {
        Properties map = getYmlByFileName(file);
        if (map == null) {
            return null;
        }
        return map.getProperty(key);
    }
}
