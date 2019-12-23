package com.wfm.servicesystem.model.vo.dict;

import com.wfm.servicesystem.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 字典类型 查询参数对象
* </p>
*
* @author wfm
* @since 2019-12-09
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictQueryParam对象", description = "字典类型查询参数")
public class DictQueryParam extends PageModel {
     private static final long serialVersionUID = 1L;

}

