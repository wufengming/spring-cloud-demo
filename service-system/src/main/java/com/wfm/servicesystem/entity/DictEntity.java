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
* 字典类型
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_dict")
@ApiModel(value="Dict对象", description="字典类型")
public class DictEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级 默认0")
    private Long parentId;

    @ApiModelProperty(value = "组织id")
    private Long orgid;

    @ApiModelProperty(value = "模块类型 ：system,gq")
    private String module;

    @ApiModelProperty(value = "数据类型：private，protect，public")
    private String types;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "层次")
    private Integer levels;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "系统内置 默认0-不是内置  1-内置,无法删除")
    private Integer isSystem;

    @ApiModelProperty(value = "删除标志  默认0-未删除  1-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "有效标志 默认1-有效  0-无效")
    private Integer enabledmark;

    @ApiModelProperty(value = "描述")
    private String description;


}
