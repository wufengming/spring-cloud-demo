package com.wfm.servicesystem.model.vo.user;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: UserQueryParam
 * date: 2019-10-31 15:19
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserQueryParam对象", description = "系统用户查询参数")
public class UserQueryParam extends PageModel {
    private static final long serialVersionUID = 1L;
}
