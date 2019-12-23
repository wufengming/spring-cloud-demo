package com.wfm.servicesystem.model.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: RoleAuthorizeVo
 * date: 2019-11-22 11:46
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value="RoleAuthorizeVo对象", description="角色授权表")
public class RoleAuthorizeVo implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "模块类型1-菜单 2-按钮 3-表单")
    private Integer type;

    @ApiModelProperty(value = "模块主键 (菜单，按钮，表单)")
    private Long menuId;

    @ApiModelProperty(value = "对象分类1-角色 2-部门")
    private Integer objecttype;

    @ApiModelProperty(value = "对象主键")
    private Long objectId;
}
