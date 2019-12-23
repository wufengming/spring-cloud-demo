package com.wfm.servicesystem.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@ApiModel(value = "查询基本参数")
public class ParamBaseModel implements Serializable {

    @ApiModelProperty(value = "用户主键")
    private long userId;

    @ApiModelProperty(value = "组织主键")
    private long orgId;
}
