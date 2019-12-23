package com.wfm.servicesystem.model.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: UserOrgRoleVo
 * date: 2019-11-11 14:58
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserOrgRoleVo对象", description = "系统用户-组织-角色关系")
public class UserOrgRoleVo implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    @ApiModelProperty(value = "组织主键")
    private Long orgid;

    @ApiModelProperty(value = "组织编码")
    private String orgcode;

    @ApiModelProperty(value = "角色主键")
    private Long rolePhid;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "版本号")
    private  Integer recordVer;
}
