package com.wfm.servicesystem.config.jwt;

import com.wfm.servicecommon.exception.ShiroConfigException;
import com.wfm.servicesystem.common.utils.JwtUtil;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.service.LoginService;
import com.wfm.servicesystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * description: Shiro 授权认证
 * 参照jdbcRealm，实现自定义realm，主要完成shiro的登录和授权工作
 * date: 2019-12-20 16:42
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {


    @Autowired
    private LoginService loginService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JwtToken;
    }
    /**
     * 授权认证
     * 设置角色/权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");

        // 设置角色/权限信息
        JwtToken jwtToken = (JwtToken) principalCollection.getPrimaryPrincipal();

        // 获取username
        String username = jwtToken.getUsername();

        // 获取登陆用户角色权限信息
        UserEntity userModel = loginService.findByUserName(username);

        // 模拟角色 权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 设置角色
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        authorizationInfo.setRoles(roleSet);
        // 设置权限
        Set<String> permissionSet = new HashSet<>();
        permissionSet.add("view,add,edit");
        authorizationInfo.setStringPermissions(permissionSet);

        return authorizationInfo;
    }

    /**
     * 登陆认证
     * 用此方法进行用户名正确与否验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");

        JwtToken jwtToken = (JwtToken) authenticationToken;
        if (jwtToken == null) {
            throw new AuthenticationException("JwtToken对象不能为空");
        }
        // 校验token
        String token = jwtToken.getToken();

        String salt = jwtToken.getSalt();
        if (StringUtils.isBlank(salt)) {
            throw new AuthenticationException("salt不能为空");
        }


        return new SimpleAuthenticationInfo(jwtToken, salt, getName());
    }
}
