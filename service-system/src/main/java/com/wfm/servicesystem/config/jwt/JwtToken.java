package com.wfm.servicesystem.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wfm.servicecommon.utils.IpUtil;
import com.wfm.servicesystem.common.utils.JwtUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.HostAuthenticationToken;

import java.util.Date;


/**
 * description: JwtToken
 * date: 2019-12-20 16:24
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class JwtToken implements HostAuthenticationToken {

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

    private String principal;

    private String credentials;

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public static JwtToken build(String token, String username, String salt, long expireSecond) {
        DecodedJWT decodedJWT = JwtUtil.getJwtInfo(token);
        Date createDate = decodedJWT.getIssuedAt();
        Date expireDate = decodedJWT.getExpiresAt();
        return new JwtToken()
                .setUsername(username)
                .setToken(token)
                .setHost(IpUtil.getRequestIp())
                .setSalt(salt)
                .setCreateDate(createDate)
                .setExpireSecond(expireSecond)
                .setExpireDate(expireDate);

    }
}
