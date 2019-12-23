package com.wfm.servicesystem.config.swagger;

import com.wfm.servicesystem.common.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * - @Api()用于类；表示标识这个类是swagger的资源
 * - @ApiOperation()用于方法；表示一个http请求的操作
 * - @ApiParam()用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
 * - @ApiModel()用于类； 表示对类进行说明，用于参数用实体类接收
 * - @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
 * - @ApiIgnore()用于类，方法，方法参数； 表示这个方法或者类被忽略
 * - @ApiImplicitParam() 用于方法； 表示单独的请求参数
 * - @ApiImplicitParams() 用于方法；包含多个 @ApiImplicitParam
 *
 * 具体使用举例说明：
 * @Api()
 * 用于类；表示标识这个类是swagger的资源
 * tags–表示说明
 * value–也是说明，可以使用tags替代
 * 但是tags如果有多个值，会生成多个list
 */


/**
 * @author wfm
 * @title: Swagger2Config
 * @projectName springboot3
 * @description: TODO
 * @date 2019/7/2015:56
 */
// @Profile({"dev","test"})
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2Config {

    /**
     * 标题
     */
    @Value("${swagger.title}")
    private String title;

    /**
     * 基本包
     */
    @Value("${swagger.base.package}")
    private String basePackage;

    /**
     * 描述
     */
    @Value("${swagger.description}")
    private String description;

    /**
     * 服务条款网址
     */
    @Value("${swagger.url}")
    private String url;

    /**
     * 作者
     */
    @Value("${swagger.contact.name}")
    private String contactName;

    /**
     * 作者网址
     */
    @Value("${swagger.contact.url}")
    private String contactUrl;

    /**
     * 作者邮箱
     */
    @Value("${swagger.contact.email}")
    private String contactEmail;

    /**
     * 版本
     */
    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 设置basePackage会将包下的所有类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                // 只有标记@ApiOperation才会暴露出给swagger
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setHeaderToken());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                // 描述
                .description(description)
                // 服务条款网址
                .termsOfServiceUrl(url)
                //版本
                .version(version)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }

    private List<Parameter> setHeaderToken() {
        List<Parameter> pars = new ArrayList<>();

        // token请求头
        String testTokenValue = "";
        ParameterBuilder tokenPar = new ParameterBuilder();
        Parameter tokenParameter = tokenPar
                .name(JwtTokenUtil.getTokenName())
                .description("Token Request Header")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue(testTokenValue)
                .build();
        pars.add(tokenParameter);
        return pars;
    }
}
