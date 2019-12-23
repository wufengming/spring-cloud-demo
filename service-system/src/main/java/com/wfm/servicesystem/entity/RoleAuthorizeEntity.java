package com.wfm.servicesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.wfm.servicesystem.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色授权表
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_role_authorize")
@ApiModel(value="RoleAuthorizeEntity对象", description="角色授权表")
public class RoleAuthorizeEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "模块主键 (菜单，按钮，表单)")
    private Long menuId;

    @ApiModelProperty(value = "模块类型1-菜单 2-按钮 3-表单")
    private Integer type;

    @ApiModelProperty(value = "对象分类1-角色 2-部门")
    private Integer objecttype;

    @ApiModelProperty(value = "对象主键")
    private Long objectId;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;


}
