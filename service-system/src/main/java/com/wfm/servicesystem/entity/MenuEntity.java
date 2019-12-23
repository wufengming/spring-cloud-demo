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
* 菜单模块
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_menu")
@ApiModel(value="Menu对象", description="菜单模块")
public class MenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "目标")
    private String target;

    @ApiModelProperty(value = "类型 0-无页面 1-框架页面 2-弹出页面 3-新窗口")
    private Integer types;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单")
    private Integer ismenu;

    @ApiModelProperty(value = "展开")
    private Integer isexpand;

    @ApiModelProperty(value = "公共")
    private Integer ispublic;

    @ApiModelProperty(value = "允许编辑")
    private Integer allowedit;

    @ApiModelProperty(value = "允许删除")
    private Integer allowdelete;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "有效标志   0启用 -1禁用")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;


}
