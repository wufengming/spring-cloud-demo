package com.wfm.servicesystem.model.vo.auth;

import com.wfm.servicesystem.model.vo.organize.OrganizeQueryVo;
import com.wfm.servicesystem.model.vo.role.RoleQueryVo;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * description: UserInfo
 * date: 2019-12-13 15:12
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserInfo对象", description = "系统用户基本信息")
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "用户基本信息")
    private UserQueryVo user;

    @ApiModelProperty(value = "组织信息")
    private List<OrganizeQueryVo> orgList;

    @ApiModelProperty(value = "角色信息")
    private List<RoleQueryVo> roleList;

    @ApiModelProperty(value = "功能权限信息")
    private List<PermissionsVo> permissions;
}
