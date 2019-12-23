package com.wfm.servicesystem.model.vo.role;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: RoleQueryParam
 * date: 2019-11-07 17:45
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RoleQueryParam对象", description = "系统角色查询参数")
public class RoleQueryParam extends PageModel {
    private static final long serialVersionUID = 1L;
}
