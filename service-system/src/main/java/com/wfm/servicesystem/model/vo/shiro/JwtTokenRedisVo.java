package com.wfm.servicesystem.model.vo.shiro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * JwtToken Redis缓存对象
 * description: JwtTokenRedisVo
 * date: 2019-12-23 10:46
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class JwtTokenRedisVo implements Serializable {
    private static final long serialVersionUID = 1831633309466775223L;
    /**
     * 登陆ip
     */
    private String host;
    /**
     * 登陆用户名称
     */
    private String username;
    /**
     * 登陆盐值
     */
    private String salt;
    /**
     * 登陆token
     */
    private String token;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 多长时间过期，默认一小时
     */
    private long expireSecond;
    /**
     * 过期日期
     */
    private Date expireDate;
}
