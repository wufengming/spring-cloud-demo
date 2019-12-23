package ${package.Entity?replace('.entity','')}.model.vo.${entity?replace('Entity','')?lower_case};

import ${package.Entity?replace('.entity','')}.model.base.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* ${table.comment!} 查询参数对象
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "${entity?replace('Entity','')}QueryParam对象", description = "${table.comment!}查询参数")
public class ${entity?replace('Entity','')}QueryParam extends PageModel {
     private static final long serialVersionUID = 1L;

}

