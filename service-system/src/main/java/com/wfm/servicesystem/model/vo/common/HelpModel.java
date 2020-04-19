package com.wfm.servicesystem.model.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: 编码-名称
 * date: 2019-12-31 09:49
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "HelpModel对象", description = "编码-名称 公用的model")
public class HelpModel implements Serializable {

    private static final long serialVersionUID = 529585770807979768L;

    @ApiModelProperty("Id")
    private long id;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private  String name;
}
