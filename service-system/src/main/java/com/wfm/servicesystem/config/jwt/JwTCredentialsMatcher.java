package com.wfm.servicesystem.config.jwt;

import com.wfm.servicesystem.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * description: JwTCredentialsMatcher
 * date: 2019-12-20 17:42
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
public class JwTCredentialsMatcher implements CredentialsMatcher {

    /**
     * JWT证书匹配
     * @param authenticationToken
     * @param authenticationInfo
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        // String token = authenticationToken.getCredentials().toString();
        String token = ((JwtToken)authenticationToken).getToken();

        String salt = authenticationInfo.getCredentials().toString();

        try {
            return JwtUtil.verifyToken(token, salt);
        } catch (Exception e) {
            log.error("JwTCredentialsMatcher Exception:" + e.getMessage(), e);
        }
        return false;
    }
}
