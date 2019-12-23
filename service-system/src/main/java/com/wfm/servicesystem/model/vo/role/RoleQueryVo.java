package com.wfm.servicesystem.model.vo.role;

import com.wfm.servicesystem.model.vo.user.UserOrgRoleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * description: RoleQueryVo
 * date: 2019-11-07 17:53
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "RoleQueryVo对象", description = "系统角色查询参数")
public class RoleQueryVo implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "分类")
    private Integer category;

    @ApiModelProperty(value = "编号")
    private String encode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "允许编辑")
    private Integer allowedit;

    @ApiModelProperty(value = "允许删除")
    private Integer allowdelete;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "版本号")
    private  Integer recordVer;

    @ApiModelProperty(value = "角色权限功能")
    private List<RoleAuthorizeVo> roleauthorizelist;
}
