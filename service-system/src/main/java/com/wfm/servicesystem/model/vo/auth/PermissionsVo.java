package com.wfm.servicesystem.model.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: PermissionsVo
 * date: 2019-12-13 15:28
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PermissionsVo对象", description = "功能权限信息")
public class PermissionsVo implements Serializable {

    @ApiModelProperty(value = "菜单编码")
    private String encode;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单url")
    private String url;

    @ApiModelProperty(value = "菜单类型 0-无页面 1-框架页面 2-弹出页面 3-新窗口")
    private Integer types;

    @ApiModelProperty(value = "页面执行的按钮权限")
    private Object[] actions;

}
