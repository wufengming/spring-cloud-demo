package com.wfm.servicesystem.model.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: SysLoginModel
 * date: 2019-12-11 10:41
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value="登录对象", description="登录对象")
public class SysLoginModel implements Serializable {

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "验证码key")
    private String checkKey;
}
