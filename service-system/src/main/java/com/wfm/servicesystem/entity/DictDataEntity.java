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
* 字典数据
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_dict_data")
@ApiModel(value="DictData对象", description="字典数据")
public class DictDataEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级 默认0")
    private Long parentId;

    @ApiModelProperty(value = "类型主键")
    private Long dictId;

    @ApiModelProperty(value = "创建组织id")
    private Long orgid;

    @ApiModelProperty(value = "数据类型：private，protect，public")
    private String types;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "默认值 0-否 1-是")
    private Integer isDefault;

    @ApiModelProperty(value = "层次")
    private Integer levels;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "系统内置  默认0-不是内置  1-内置,无法删除")
    private Integer isSystem;

    @ApiModelProperty(value = "删除标志  默认0-未删除  1-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "有效标志 默认1-有效  0-无效")
    private Integer enabledmark;

    @ApiModelProperty(value = "描述")
    private String description;


}
