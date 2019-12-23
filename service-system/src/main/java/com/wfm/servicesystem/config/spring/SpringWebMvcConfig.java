package com.wfm.servicesystem.config.spring;


import com.wfm.servicesystem.common.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description: SpringWebMvcConfig
 * date: 2019-11-19 19:21
 * author: wfm
 * version: 1.0
 */
@Slf4j
@Configuration
@ComponentScan//组件扫描
public class SpringWebMvcConfig implements WebMvcConfigurer {
    /**
     *  系统-信息配置
     */
    @Autowired
    private SystemProperties systemProperties;


    /**
     * 匹配路由请求规则
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        /*
         * 设置是否是后缀模式匹配
         * 1.ServletMappings 设置的是 "/" 2.setUseSuffixPatternMatch默认设置为true,
         * 那么,"/user" 就会匹配 "/user.*",也就是说,"/user.html" 的请求会被 "/user" 的 Controller所拦截.
         * 3.如果该值为false,则不匹配
         */

        configurer.setUseSuffixPatternMatch(false);

        /*
         * 设置是否自动后缀路径模式匹配
         * setUseTrailingSlashMatch的默认值为true
         * 也就是说, "/user" 和 "/user/" 都会匹配到 "/user"的Controller
         */
        configurer.setUseTrailingSlashMatch(true);
    }

    /**
     * 添加类型转换器和格式化器
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }
    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // 设置项目静态资源访问
        String resourceHandlers = systemProperties.getResourceHandlers();

        //过滤swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");



        /**
         * 说明：增加虚拟路径(在此处配置的虚拟路径，用springboot内置的tomcat时有效，
         用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
         */
        //registry.addResourceHandler("/pic/**").addResourceLocations("file:E:/pic/");

        // 设置上传文件访问路径
        registry.addResourceHandler(systemProperties.getResourceAccessPatterns())
                .addResourceLocations("file:" + systemProperties.getUploadPath());
    }

}
