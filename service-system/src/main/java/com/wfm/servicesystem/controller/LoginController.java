package com.wfm.servicesystem.controller;

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.utils.CommonUtil;
import com.wfm.servicecommon.utils.PasswordUtil;
import com.wfm.servicesystem.common.base.BaseController;
import com.wfm.servicesystem.common.utils.JwtTokenUtil;
import com.wfm.servicesystem.common.utils.JwtUtil;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.model.base.InfoBaseModel;
import com.wfm.servicesystem.model.enums.EnableStateEnum;
import com.wfm.servicesystem.model.vo.login.LoginUserTokenVo;
import com.wfm.servicesystem.model.vo.login.SysLoginModel;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import com.wfm.servicesystem.service.LoginService;
import com.wfm.servicesystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 登陆控制器
 * date: 2019-12-11 10:32
 * author: wfm
 * version: 1.0
 */
@Slf4j
@RestController
@Api("登陆控制器")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "系统用户登陆接口", response = ApiResult.class)
    public ApiResult<Boolean> login(@Valid @RequestBody SysLoginModel sysLogin,HttpServletResponse response) throws Exception {



        String username = sysLogin.getUsername();
        String password = sysLogin.getPassword();

        // 验证码校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ApiResult.fail("账号和密码不能为空！");
        }

        LoginUserTokenVo loginUserTokenVo = loginService.login(sysLogin);
        // 设置token响应头
        response.setHeader(JwtTokenUtil.getTokenName(), loginUserTokenVo.getToken());
        return ApiResult.ok(loginUserTokenVo, "登陆成功");
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录", notes = "退出接口", response = ApiResult.class)
    public ApiResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 用户退出逻辑
        String token=request.getHeader("Access-Token");
        if(CommonUtil.isEmpty(token)) {
            return ApiResult.fail("token 为空,退出登录失败！");
        }

        // 清空服务器的用户缓存信息

        loginService.logout(request);

        return ApiResult.ok("退出成功");
    }


    /**
     * 获取系统用户 的权限（组织信息，角色信息，菜单和按钮）
     */
    @PostMapping("/permissions")
    @ApiOperation(value = "获取系统用户权限信息", notes = "查看用户权限信息", response = UserQueryVo.class)
    public ApiResult<UserQueryVo> getSysUser(@Valid @RequestBody InfoBaseModel infoModel) throws Exception {
        if(CommonUtil.isEmpty(infoModel.getUserId()) || CommonUtil.isEmpty(infoModel.getOrgId())){
            return ApiResult.fail("用户主键或者组织主键为空，请确认信息！");
        }

        //

        //UserQueryVo sysUserQueryVo = userService.getSysUserById(infoModel);
        return ApiResult.ok();
    }
}
