package com.wfm.servicesystem.model.vo.menu;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 菜单模块 查询参数对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MenuQueryParam对象", description = "菜单模块查询参数")
public class MenuQueryParam extends PageModel {
     private static final long serialVersionUID = 1L;

}

