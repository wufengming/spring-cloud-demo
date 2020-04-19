package com.wfm.servicesystem.model.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * description: PermissionMenu
 * date: 2019-12-31 14:16
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value="PermissionMenu对象", description="权限菜单")
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = -1081027117796438013L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父级")
    private Long parentId;

    @ApiModelProperty(value = "层次")
    private Integer levels;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "连接地址")
    private String path;

    @ApiModelProperty(value = "类型 0-无页面 1-框架页面 2-弹出页面 3-新窗口")
    private Integer types;

    @ApiModelProperty(value = "菜单拥有的按钮的权限")
    private List<PermissionMenuButton> actions;
}
