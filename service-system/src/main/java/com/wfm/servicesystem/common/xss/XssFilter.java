package com.wfm.servicesystem.common.xss;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description: Xss过滤器
 * date: 2019-11-29 11:30
 * author: wfm
 * version: 1.0
 */
@Slf4j
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
//@Order(1)
public class XssFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----xssFilter 过滤器初始化----");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(xssHttpServletRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("---- xssFilter 过滤器销毁----");
    }

}
