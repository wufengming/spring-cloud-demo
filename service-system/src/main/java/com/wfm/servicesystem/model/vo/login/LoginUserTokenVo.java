package com.wfm.servicesystem.model.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: LoginUserTokenVo
 * date: 2019-12-22 16:24
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel("登陆用户信息")
public class LoginUserTokenVo implements Serializable {

    private static final long serialVersionUID = -4650803752566647697L;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("登陆用户对象")
    private LoginUserVo loginUserVo;

}
