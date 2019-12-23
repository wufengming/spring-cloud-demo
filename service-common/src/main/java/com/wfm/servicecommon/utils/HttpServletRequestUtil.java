package com.wfm.servicecommon.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * description: 获取当前请求的HttpServletRequest对象
 * date: 2019-12-22 14:27
 *
 * @author: wfm
 * @version: 1.0
 */
public class HttpServletRequestUtil {
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
