package com.wfm.servicesystem.config.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import com.wfm.servicesystem.common.utils.converter.*;

import java.util.Date;

/**
 * description: 转换器配置
 * date: 2019-11-29 17:07
 * author: wfm
 * version: 1.0
 */
@Configuration
public class ConverterConfig {
    @Bean
    public Converter<String, Date> stringToDateConverter() {

        return new StringToDateConverter();
    }

    @Bean
    public Converter<String, Integer> stringToIntegerConverter() {

        return new StringToIntegerConverter();
    }

    @Bean
    public Converter<String, Double> stringToDoubleConverter() {
        return new StringToDoubleConverter();
    }
}
