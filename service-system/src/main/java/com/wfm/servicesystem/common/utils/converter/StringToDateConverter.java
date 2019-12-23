
package com.wfm.servicesystem.common.utils.converter;

import com.wfm.servicesystem.common.utils.StringToDateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * <code>
 * 日期转换器,将请求参数的日期字符串转换成java.util.Date类型
 * </code>
 * @author geekidea
 * @date 2018-11-08
 */
public class StringToDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		return StringToDateUtil.convert(source);
	}
}
