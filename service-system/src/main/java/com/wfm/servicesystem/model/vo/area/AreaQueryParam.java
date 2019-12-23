package com.wfm.servicesystem.model.vo.area;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 行政区域表 查询参数对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AreaQueryParam对象", description = "行政区域表查询参数")
public class AreaQueryParam extends PageModel {
     private static final long serialVersionUID = 1L;

}

