package com.wfm.servicesystem.common.base;


import com.wfm.servicecommon.api.Apicontroller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: BaseController
 * date: 2019-10-24 18:15
 * author: wfm
 * version: 1.0
 */
@Slf4j
public abstract class BaseController extends Apicontroller {
    /**
     * 获取当前请求
     *
     * @return request
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    /**
     * 获取当前请求
     *
     * @return response
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
