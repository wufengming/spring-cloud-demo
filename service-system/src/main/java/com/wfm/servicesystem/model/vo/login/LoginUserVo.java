package com.wfm.servicesystem.model.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * description: LoginUserVo
 * date: 2019-12-22 16:29
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class LoginUserVo implements Serializable {

    private static final long serialVersionUID = -1758338570596088158L;

    @ApiModelProperty("用户Id")
    private long userId;

    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("组织id")
    private Long orgId;

    @ApiModelProperty("组织编码")
    private Long orgCode;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("角色编码")
    private Set<String> roles;

    @ApiModelProperty("权限编码列表")
    private Set<String> permissionCodes;
}
