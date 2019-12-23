package com.wfm.servicesystem.common.xss;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * description: XssHttpServletRequestWrapper
 * date: 2019-11-29 11:39
 * author: wfm
 * version: 1.0
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String strHeader = super.getHeader(name);
        if(StringUtils.isEmpty(strHeader)){
            return strHeader;

        }

        // Whitelist.none 该API会清除所有HTML标签，仅保留文本节点
        // Whitelist.simpleText 该API仅会保留b, em, i, strong, u 标签，除此之外的所有HTML标签都会被清除
        // Whitelist.basic 该API会保留 a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre,
                        // q, small, span, strike, strong, sub, sup, u, ul 和其适当的属性标签，除此之外的所有HTML标签都会被清除，
                        // 且该API不允许出现图片(img tag)。另外该API中允许出现的超链接中可以允许其指定http, https, ftp, mailto
                        // 且在超链接中强制追加rel=nofollow属性。

        // Whitelist.basicWithImages  该API在保留basic()中允许出现的标签的同时也允许出现图片(img tag)和img的相关适当属性，且其src允许其指定 http 或 https
        // Whitelist.relaxed 该API仅会保留 a, b, blockquote, br, caption, cite, code, col, colgroup, dd, div,
                            // dl, dt, em, h1, h2, h3, h4, h5, h6, i, img, li, ol, p, pre, q, small, span, strike, strong, sub,
                            // sup, table, tbody, td, tfoot, th, thead, tr, u, ul 标签，除此之外的所有HTML标签都会被清除，
                            // 且在超链接中不会强制追加rel=nofollow属性。

        return Jsoup.clean(super.getHeader(name), Whitelist.relaxed());
    }

    @Override
    public String getQueryString() {

        String strQueryString = super.getQueryString();
        if(StringUtils.isEmpty(strQueryString)){
            return strQueryString;
        }
        return Jsoup.clean(super.getQueryString(),Whitelist.relaxed());
    }

    @Override
    public String getParameter(String name) {

        String strParameter = super.getParameter(name);
        if(StringUtils.isEmpty(strParameter)){
            return strParameter;
        }
        return Jsoup.clean(super.getParameter(name),Whitelist.relaxed());
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values==null){
            return values;
        }

        int length = values.length;
        String[] escapseValues = new String[length];

        for(int i = 0;i<length;i++){
            //过滤一切可能的xss攻击字符串
            escapseValues[i] = Jsoup.clean(values[i], Whitelist.relaxed()).trim();
            if(!StringUtils.equals(escapseValues[i],values[i])){
                log.debug("xss字符串过滤前："+values[i]+"\r\n"+"过滤后："+escapseValues[i]);
            }
        }
        return escapseValues;
    }
}
