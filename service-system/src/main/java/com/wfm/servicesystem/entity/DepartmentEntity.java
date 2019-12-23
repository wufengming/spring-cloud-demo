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
 * 部门
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_department")
@ApiModel(value="DepartmentEntity对象", description="部门")
public class DepartmentEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父级")
    private Long parentId;

    @ApiModelProperty(value = "组织id")
    private Long orgid;

    @ApiModelProperty(value = "组织编码")
    private String orgcode;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "层次")
    private Integer levels;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;


}
