package com.wfm.servicecommon.constant;

/**
 * description: CommonConstant
 * date: 2019-12-18 11:38
 *
 * @author: wfm
 * @version: 1.0
 */
public interface CommonConstant {

    /**
     * 系统日志类型： 登录
     */
    int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    int LOG_TYPE_2 = 2;

    /**
     * 系统日志类型： 定时任务
     */
    int LOG_TYPE_3 = 3;

    /**
     * 登陆token
     */
    String JWT_DEFAULT_TOKEN_NAME = "Access-Token";
    /**
     * JWT用户名
     */
    String JWT_USERNAME = "username";
    /**
     * JWT刷新新token响应状态码
     */
    int JWT_REFRESH_TOKEN_CODE = 460;

    /**
     * JWT刷新新token响应状态码，
     * Redis中不存在，但jwt未过期，不生成新的token，返回361状态码
     */
    int JWT_INVALID_TOKEN_CODE = 461;

    /**
     * JWT Token默认密钥
     */
    String JWT_DEFAULT_SECRET = "123456";

    /**
     * JWT 默认过期时间，3600L，单位秒
     */
    Long JWT_DEFAULT_EXPIRE_SECOND = 3600L;
}
