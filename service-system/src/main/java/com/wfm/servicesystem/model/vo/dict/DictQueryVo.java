package com.wfm.servicesystem.model.vo.dict;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* <p>
* 字典类型 查询结果对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@ApiModel(value="DictQueryVo对象", description="字典类型 查询参数")
public class DictQueryVo implements Serializable {
  private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private Long id;

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

    @ApiModelProperty(value = "版本号")
    private  Integer recordVer;


}
