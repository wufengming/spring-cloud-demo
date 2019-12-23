package com.wfm.servicesystem.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wfm.servicecommon.constant.CommonConstant;
import com.wfm.servicecommon.utils.CommonUtil;
import com.wfm.servicesystem.common.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

/**
 * description: JWT工具类
 * date: 2019-12-18 18:00
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
@Component
public class JwtUtil {

    private static JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        JwtUtil.jwtProperties = jwtProperties;
    }

    /**
     * 生成JWT Token
     *
     * @param username       用户名
     * @param password       JWT加密密码
     * @param expireDuration 过期时间和单位
     * @return token
     */
    public static String generateToken(String username, String password, Duration expireDuration) {
        try {
            if (StringUtils.isBlank(username)) {
                log.error("用户名不能为空");
                return null;
            }

            // 如果盐值为空，则使用默认值：123456
            if (StringUtils.isBlank(password)) {
                password = jwtProperties.getSecret();
            }

            // 过期时间，单位：秒
            Long expireSecond;
            // 默认过期时间为1小时
            if (expireDuration == null) {
                expireSecond = jwtProperties.getExpireSecond();
            } else {
                expireSecond = expireDuration.getSeconds();
            }

            Date expireDate = DateUtils.addSeconds(new Date(), expireSecond.intValue());

            // 生成token 有密钥的 HS256 签名算法进行签名
            Algorithm algorithm = Algorithm.HMAC256(password);
            String token = JWT.create()
                    .withClaim(CommonConstant.JWT_USERNAME, username)
                    // jwt唯一id
                    .withJWTId(CommonUtil.getUUID())
                    // 签发人
                    .withIssuer(jwtProperties.getIssuer())
                    // 主题
                    .withSubject(jwtProperties.getSubject())
                    // 签发的目标
                    .withAudience(jwtProperties.getAudience())
                    // 签名时间
                    .withIssuedAt(new Date())
                    // token过期时间
                    .withExpiresAt(expireDate)
                    // 签名
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            log.error("generateToken exception", e);
        }
        return null;
    }

    public static boolean verifyToken(String token, String password) {
        try {

            // 如果盐值为空，则使用默认值：123456
            if (StringUtils.isBlank(password)) {
                password = jwtProperties.getSecret();
            }

            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    // 签发人
                    .withIssuer(jwtProperties.getIssuer())
                    // 主题
                    .withSubject(jwtProperties.getSubject())
                    // 签发的目标
                    .withAudience(jwtProperties.getAudience())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            if (jwt != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("Verify Token Exception", e);
            return false;
        }
        return false;
    }

    /**
     * 解析token，获取token数据
     *
     * @param token
     * @return
     */
    public static DecodedJWT getJwtInfo(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT;
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        DecodedJWT decodedJWT = getJwtInfo(token);
        if (decodedJWT == null) {
            return null;
        }
        String username = decodedJWT.getClaim(CommonConstant.JWT_USERNAME).asString();
        return username;
    }

    /**
     * 获取创建时间
     *
     * @param token
     * @return
     */
    public static Date getIssuedAt(String token) {
        DecodedJWT decodedJWT = getJwtInfo(token);
        if (decodedJWT == null) {
            return null;
        }
        return decodedJWT.getIssuedAt();
    }

    /**
     * 获取过期时间
     *
     * @param token
     * @return
     */
    public static Date getExpireDate(String token) {
        DecodedJWT decodedJWT = getJwtInfo(token);
        if (decodedJWT == null) {
            return null;
        }
        return decodedJWT.getExpiresAt();
    }

    /**
     * 判断token是否已过期
     *
     * @param token
     * @return
     */
    public static boolean isExpired(String token) {
        Date expireDate = getExpireDate(token);
        if (expireDate == null) {
            return true;
        }
        return expireDate.before(new Date());
    }
}
