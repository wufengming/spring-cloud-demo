package com.wfm.servicesystem.model.vo.menubutton;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 菜单按钮 查询参数对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MenuButtonQueryParam对象", description = "菜单按钮查询参数")
public class MenuButtonQueryParam extends PageModel {
     private static final long serialVersionUID = 1L;

}

