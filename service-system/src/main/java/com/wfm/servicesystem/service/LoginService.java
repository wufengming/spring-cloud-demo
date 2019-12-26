package com.wfm.servicesystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wfm.servicesystem.config.jwt.JwtToken;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.model.vo.login.LoginUserTokenVo;
import com.wfm.servicesystem.model.vo.login.LoginUserVo;
import com.wfm.servicesystem.model.vo.login.SysLoginModel;
import com.wfm.servicesystem.model.vo.shiro.LoginUserRedisVo;

import javax.servlet.http.HttpServletRequest;

/**
 * description: LoginService
 * date: 2019-12-21 16:18
 *
 * @author: wfm
 * @version: 1.0
 */
public interface LoginService extends IService<UserEntity> {

    /**
     * 登陆
     *
     * @param loginParam
     * @return
     * @throws Exception
     */
    LoginUserTokenVo login(SysLoginModel loginParam) throws Exception;

    /**
     * 退出
     *
     * @param request
     */
    void logout(HttpServletRequest request) throws Exception;

    /**
     * 根据用户名获取系统用户对象
     * @param userName
     * @return
     */
    UserEntity findByUserName(String userName);

    /**
     * 缓存登陆信息
     *
     * @param jwtToken
     * @param loginUserVo
     */
    void cacheLoginInfo(JwtToken jwtToken, LoginUserVo loginUserVo);


    /**
     * 刷新登陆信息
     * @param oldToken
     * @param username
     * @param newJwtToken
     */
    void refreshLoginInfo(String oldToken, String username, JwtToken newJwtToken);

    /**
     * 缓存用户信息
     * @param username
     * @return
     */
    LoginUserVo getLoginUserVo(String username);

    /**
     * 缓存用户信息，包含【客户端信息对象】
     * @param username
     * @return
     */
    LoginUserRedisVo getLoginUserRedisVo(String username);

    /**
     * 判断token在redis中是否存在
     * @param token
     * @return
     */
    boolean existsByRedis(String token);

    /**
     * 删除当前用户登陆缓存
     * @param token
     * @param username
     */
    void deleteUserInfoByRedis(String token, String username);

    /**
     * 删除所有用户登陆缓存（all）
     * @param username
     */
    void deleteUserAllByRedis(String username);
}
