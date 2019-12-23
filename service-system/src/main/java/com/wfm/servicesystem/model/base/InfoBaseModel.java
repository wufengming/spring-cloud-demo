package com.wfm.servicesystem.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 单据信息的参数:id
 * @author wfm
 * @Date 2019/8/21
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "单据信息的参数:id")
public class InfoBaseModel extends ParamBaseModel {

    @ApiModelProperty(value = "表单主键")
    private long id;
}
