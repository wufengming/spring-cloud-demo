package com.wfm.servicesystem.model.vo.organize;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * description: 组织 查询参数对象
 * date: 2019-11-08 17:15
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OrganizeQueryParam对象", description = "组织查询参数")
public class OrganizeQueryParam extends PageModel {
    private static final long serialVersionUID = 1L;
}
