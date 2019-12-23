package com.wfm.servicesystem.config.jwt;

import com.wfm.servicecommon.api.ApiCode;
import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.utils.HttpServletResponseUtil;
import com.wfm.servicesystem.common.properties.JwtProperties;
import com.wfm.servicesystem.common.utils.JwtTokenUtil;
import com.wfm.servicesystem.common.utils.JwtUtil;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * description: JwtFilter
 * date: 2019-12-20 17:07
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
public class JwtFilter extends AuthenticatingFilter {

    // @Autowired
    private LoginService loginService;

    // @Autowired
    private JwtProperties jwtProperties;

    public JwtFilter(LoginService loginService, JwtProperties jwtProperties) {
        this.loginService = loginService;
        this.jwtProperties = jwtProperties;
    }


    /**
     * 将JWT Token包装成AuthenticationToken
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = JwtTokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            throw new AuthenticationException("token不能为空");
        }

        if (JwtUtil.isExpired(token)) {
            throw new AuthenticationException("JWT Token已过期,token:" + token);
        }

        // 如果开启redis二次校验，或者设置为单个用户token登陆，则先在redis中判断token是否存在
//        if (jwtProperties.isRedisCheck() || jwtProperties.isSingleLogin()) {
//            boolean redisExpired = loginRedisService.exists(token);
//            if (!redisExpired) {
//                throw new AuthenticationException("Redis Token不存在,token:" + token);
//            }
//        }

        String username = JwtUtil.getUsername(token);

        UserEntity userModel = loginService.findByUserName(username);

        return JwtToken.build(token, username, userModel.getPassword(), jwtProperties.getExpireSecond());
    }

    /**
     * 判断是否允许访问
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("isAccessAllowed url:{}", url);

        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { //not found any token
            log.error("Token不能为空", e);
        } catch (Exception e) {
            log.error("访问错误", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 处理未经验证的请求
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        // 返回401
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应码为401或者直接输出消息
        String url = httpServletRequest.getRequestURI();
        log.error("JwtFilter.onAccessDenied url：{}", url);
        ApiResult apiResult = ApiResult.fail(ApiCode.UNAUTHORIZED);
        HttpServletResponseUtil.printJSON(httpServletResponse, apiResult);

        return false;
    }

    /**
     * 登陆成功处理
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("鉴权成功,token:{},url:{}", token, url);
        // 刷新token
        JwtToken jwtToken = (JwtToken) token;
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        // loginService.refreshToken(jwtToken, httpServletResponse);
        return true;
    }

    /**
     * 登陆失败处理
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("登陆失败，token:" + token + ",error:" + e.getMessage(), e);
        return false;
    }
}
