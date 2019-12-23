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
 * 组织单元
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_org_type")
@ApiModel(value="OrgTypeEntity对象", description="组织单元")
public class OrgTypeEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父级")
    private Long parentId;

    @ApiModelProperty(value = "父编码")
    private String parentCode;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "检索编码（用-区分层级信息）")
    private String orderCode;

    @ApiModelProperty(value = "名称")
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
