package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.constant.CommonRedisKey;
import com.wfm.servicecommon.utils.*;
import com.wfm.servicecommon.vo.ClientInfo;
import com.wfm.servicesystem.common.properties.JwtProperties;
import com.wfm.servicesystem.common.utils.JwtTokenUtil;
import com.wfm.servicesystem.common.utils.JwtUtil;
import com.wfm.servicesystem.common.utils.SaltUtil;
import com.wfm.servicesystem.config.jwt.JwtToken;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.mapper.UserMapper;
import com.wfm.servicesystem.model.convert.ShiroConvert;
import com.wfm.servicesystem.model.convert.UserConvert;
import com.wfm.servicesystem.model.enums.EnableStateEnum;
import com.wfm.servicesystem.model.vo.login.LoginUserTokenVo;
import com.wfm.servicesystem.model.vo.login.LoginUserVo;
import com.wfm.servicesystem.model.vo.login.SysLoginModel;
import com.wfm.servicesystem.model.vo.shiro.JwtTokenRedisVo;
import com.wfm.servicesystem.model.vo.shiro.LoginUserRedisVo;
import com.wfm.servicesystem.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.List;
import java.util.Set;


/**
 * description: LoginServiceImpl
 * date: 2019-12-21 16:19
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisUtil redisUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginUserTokenVo login(SysLoginModel loginParam) throws Exception {


        String username = loginParam.getUsername();
        String password = loginParam.getPassword();

        // 从数据库中获取登陆用户信息
        UserEntity sysUser = findByUserName(username);
        // 1.校验用户是否有效
        if (sysUser == null) {
            log.error("登陆失败,loginParam:{}", loginParam);
            throw new AuthenticationException("用户名或密码错误");
        }
        // 2.该用户已注销 或者冻结
        if (EnableStateEnum.DISABLE.getCode().equals(sysUser.getEnabledmark())) {
            throw new AuthenticationException("账号已禁用");
        }

        // 实际项目中，前端传过来的密码应先加密
        // 原始密码明文：123456
        // 原始密码前端加密： 对称加密
        // 后台加密规则：sha256(123456 + salt)
        // 3.校验用户名或密码是否正确
        String salt = sysUser.getSalt();
        String encryptPassword = PasswordUtil.encrypt(password, salt);

        if (!encryptPassword.equals(sysUser.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        // 根据用户id获取组织信息

        // 根据用户id获取角色信息


        // 获取当前用户权限
//        Set<String> permissionCodes = sysRolePermissionService.getPermissionCodesByRoleId(roleId);
//        if (CollectionUtils.isEmpty(permissionCodes)) {
//            throw new AuthenticationException("权限列表不能为空");
//        }
//        loginSysUserVo.setPermissionCodes(permissionCodes);

        // 将系统用户对象转换成登陆用户对象
        LoginUserVo loginUserVo =new LoginUserVo();
        loginUserVo.setUserId(sysUser.getId())
                .setUserAccount(sysUser.getAccount())
                .setUserName(sysUser.getRealname());


        // 生成token字符串并返回
        Long expireSecond = jwtProperties.getExpireSecond();

        String token = JwtUtil.generateToken(username, encryptPassword, Duration.ofSeconds(expireSecond));
        log.debug("token:{}", token);

        // 创建AuthenticationToken
        JwtToken jwtToken = JwtToken.build(token, username, encryptPassword, expireSecond);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 执行认证登陆
        subject.login(jwtToken);

        // 缓存登陆信息到Redis
        cacheLoginInfo(jwtToken, loginUserVo);
        // log.debug("登陆成功,username:{}", username);

        // 返回token和登陆用户信息对象
        LoginUserTokenVo loginUserTokenVo = new LoginUserTokenVo();
        loginUserTokenVo.setToken(token);
        loginUserTokenVo.setLoginUserVo(loginUserVo);
        return loginUserTokenVo;
    }

    @Override
    public UserEntity findByUserName(String userName) {

        UserEntity sysUser = new UserEntity().setAccount(userName);
        return userMapper.selectOne(new QueryWrapper(sysUser));
    }

    @Override
    public void logout(HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        // 获取token
        String token = JwtTokenUtil.getToken(request);
        String username = JwtUtil.getUsername(token);

        // 删除Redis缓存信息
        deleteUserInfoByRedis(token, username);
        log.info("退出成功,username:{},token:{}", username, token);
    }

    /**
     * key-value: 有过期时间-->token过期时间
     * 1. tokenMd5:jwtTokenRedisVo
     * 2. username:loginSysUserRedisVo
     * 3. username:salt
     * hash: 没有过期时间，统计在线的用户信息
     * username:num
     */
    @Override
    public void cacheLoginInfo(JwtToken jwtToken, LoginUserVo loginUserVo) {
        if (CommonUtil.isEmpty(jwtToken)) {
            throw new IllegalArgumentException("jwtToken不能为空");
        }
        if (loginUserVo == null) {
            throw new IllegalArgumentException("loginSysUserVo不能为空");
        }
        // token
        String token = jwtToken.getToken();
        // 盐值
        String salt = jwtToken.getSalt();
        // 登陆用户名称
        String username = loginUserVo.getUserAccount();
        // token md5值
        String tokenMd5 = DigestUtils.md5Hex(token);

        // Redis缓存JWT Token信息
        JwtTokenRedisVo jwtTokenRedisVo = ShiroConvert.INSTANCE.jwtTokenToJwtTokenRedisVo(jwtToken);

        // 用户客户端信息
        ClientInfo clientInfo = ClientInfoUtil.get(HttpServletRequestUtil.getRequest());

        // Redis缓存登陆用户信息
        // 将LoginSysUserVo对象复制到LoginSysUserRedisVo，使用mapstruct进行对象属性复制
        LoginUserRedisVo loginUserRedisVo = UserConvert.INSTANCE.loginUserVoToLoginUserRedisVo(loginUserVo);
        loginUserRedisVo.setSalt(salt);
        loginUserRedisVo.setClientInfo(clientInfo);

        // Redis过期时间与JwtToken过期时间一致
        long expireSecond = jwtToken.getExpireSecond();


        String loginTokenRedisKey = String.format(CommonRedisKey.LOGIN_TOKEN, tokenMd5);
        // 1. tokenMd5:jwtTokenRedisVo
        redisUtil.set(loginTokenRedisKey, jwtTokenRedisVo, expireSecond);
        redisUtil.expire(loginTokenRedisKey,expireSecond);
        log.info("登录缓存-token：token: {}", jwtTokenRedisVo);

        // 2. username:loginSysUserRedisVo
        redisUtil.set(String.format(CommonRedisKey.LOGIN_USER, username), loginUserRedisVo,expireSecond);
        redisUtil.expire(String.format(CommonRedisKey.LOGIN_USER, username),expireSecond);
        log.info("登录缓存-user：userInfo: {}", loginUserRedisVo);


        // 3. login user-token(存储jwtToken 的key的信息)
        redisUtil.set(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, tokenMd5), loginTokenRedisKey);
        redisUtil.expire(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, tokenMd5),expireSecond);
    }

    @Override
    public LoginUserVo getLoginUserVo(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        LoginUserRedisVo userRedisVo = getLoginUserRedisVo(username);
        return userRedisVo;
    }

    @Override
    public void refreshLoginInfo(String oldToken, String username, JwtToken newJwtToken) {
        // 获取缓存的登陆用户信息
        LoginUserRedisVo loginUserRedisVo = getLoginUserRedisVo(username);
        // 删除之前的token信息
        deleteUserInfoByRedis(oldToken, username);
        // 缓存登陆信息
        cacheLoginInfo(newJwtToken, loginUserRedisVo);
    }

    @Override
    public LoginUserRedisVo getLoginUserRedisVo(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        return (LoginUserRedisVo) redisUtil.get(String.format(CommonRedisKey.LOGIN_USER, username));
    }

    @Override
    public boolean existsByRedis(String token) {
        if (token == null) {
            throw new IllegalArgumentException("token不能为空");
        }
        String tokenMd5 = DigestUtils.md5Hex(token);
        Object object = redisUtil.get(String.format(CommonRedisKey.LOGIN_TOKEN, tokenMd5));
        return object != null;
    }

    @Override
    public void deleteUserInfoByRedis(String token, String username) {
        if (token == null) {
            throw new IllegalArgumentException("token不能为空");
        }
        if (username == null) {
            throw new IllegalArgumentException("username不能为空");
        }
        String tokenMd5 = DigestUtils.md5Hex(token);
        // 1. delete tokenMd5
        redisUtil.del(String.format(CommonRedisKey.LOGIN_TOKEN, tokenMd5));
        // 2. delete username
        redisUtil.del(String.format(CommonRedisKey.LOGIN_USER, username));
        // 3. delete user token
        redisUtil.del(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, tokenMd5));
    }

    @Override
    public void deleteUserAllByRedis(String username) {

        Set<String> userTokenKeys = redisUtil.getKeys(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, "*"));

        if (CollectionUtils.isEmpty(userTokenKeys)) {
            return;
        }

        //  获取LOGIN_TOKEN 的信息
        List<String> tokenMd5List = (List<String>)(List)redisUtil.multiGet(userTokenKeys);


        // 1. 删除登陆用户的所有token信息(LOGIN_TOKEN)
        redisUtil.delAll(tokenMd5List);
        // 2. 删除登陆用户信息(LOGIN_USER)
        redisUtil.del(String.format(CommonRedisKey.LOGIN_USER, username));
        // 3. 删除登陆用户的所有(LOGIN_USER_TOKEN) login:user:token: 信息
        redisUtil.delAll(userTokenKeys);

    }
}
