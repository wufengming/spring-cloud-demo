package com.wfm.servicesystem.model.vo.user;

import com.wfm.servicesystem.model.base.InfoBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: UpdatePasswordParam
 * date: 2019-10-31 19:15
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdatePasswordParam对象", description = "系统用户密码修改参数")
public class UpdatePasswordParam extends InfoBaseModel {

    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;


}
