package com.wfm.servicesystem.model.vo.dictdata;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 字典数据 查询参数对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictDataQueryParam对象", description = "字典数据查询参数")
public class DictDataQueryParam extends PageModel {
     private static final long serialVersionUID = 1L;

}

