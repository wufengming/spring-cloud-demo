package com.wfm.servicesystem.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * description: SystemFilterProperties
 * date: 2019-11-03 00:47
 * author: wfm
 * version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "demo-system.filter")
@Component
public class SystemFilterProperties {
    /**
     * 请求路径Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig requestPath = new FilterConfig();

    @Data
    public static class FilterConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

        /**
         * 包含的路径
         */
        private String[] includePaths;

        /**
         * 排除路径
         */
        private String[] excludePaths;

    }
}
