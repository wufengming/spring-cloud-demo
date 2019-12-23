package com.wfm.servicecommon.constant;

/**
 * redis key 常量
 * description: CommonRedisKey
 * date: 2019-12-23 11:47
 *
 * @author: wfm
 * @version: 1.0
 */
public interface CommonRedisKey {
    /**
     * 登陆用户token信息key
     */
    String LOGIN_TOKEN ="login:token:%s";

    /**
     * 登陆用户信息key
     */
    String LOGIN_USER="login:user:%s";

    /**
     * 登陆用户username token
     */
    String LOGIN_USER_TOKEN = "login:user:token:%s:%s";

    /**
     * 验证码
     */
    String VERIFY_CODE = "verify.code:%s";
}
