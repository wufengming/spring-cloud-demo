package com.wfm.servicesystem.model.vo.area;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* <p>
* 行政区域表 查询结果对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@ApiModel(value="AreaQueryVo对象", description="行政区域表 查询参数")
public class AreaQueryVo implements Serializable {
  private static final long serialVersionUID = 1L;


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

    @ApiModelProperty(value = "简拼")
    private String simplespelling;

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


}
