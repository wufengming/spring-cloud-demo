package com.wfm.servicesystem.model.vo.organize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * description: OrganizeTreeVo
 * date: 2019-11-08 17:58
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "OrganizeTreeVo对象", description = "组织")
public class OrganizeTreeVo implements Serializable {
    private static final long serialVersionUID = -2250233632748939400L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父级")
    private Long parentId;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    private Integer enabledmark;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "版本")
    private Integer recordVer;

    private List<OrganizeTreeVo> children;

}
