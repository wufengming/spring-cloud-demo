package com.wfm.servicesystem.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: SystemProperties
 * date: 2019-11-03 00:37
 * author: wfm
 * version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "demo-system")
@Component
public class SystemProperties {
    /**
     * 是否启用ansi控制台输出有颜色的字体，local环境建议开启，服务器环境设置为false
     */
    private boolean enableAnsi;

    /**
     * 是否启用验证码
     */
    private boolean enableVerifyCode;

    /**
     * 拦截器配置
     */
    @NestedConfigurationProperty
    private SystemInterceptorProperties interceptor;

    /**
     */
    @NestedConfigurationProperty
    private SystemFilterProperties filter;

    /**
     * 上传目录(实际存储路径)
     */
    private String uploadPath;
    /**
     * 资源访问路径，前端访问
     */
    private String resourceAccessPath;
    /**
     * 资源访问路径，后段配置，资源映射/拦截器使用
     */
    private String resourceAccessPatterns;
    /**
     * 资源访问全路径
     */
    private String resourceAccessUrl;

    /**
     * 允许上传的文件后缀集合
     */
    private List<String> allowUploadFileExtensions;
    /**
     * 允许下载的文件后缀集合
     */
    private List<String> allowDownloadFileExtensions;

    /**
     * 项目静态资源访问配置
     *
     * @see SystemProperties addResourceHandlers
     */
    private String resourceHandlers;
}
