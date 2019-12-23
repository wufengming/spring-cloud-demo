package ${package.Mapper};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import ${package.Entity?replace('.entity','')}.model.base.PageModel;
import ${package.Entity?replace('.entity','')}.model.vo.${entity?replace('Entity','')?lower_case}.${entity?replace('Entity','')}QueryVo;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
* <p>
* ${table.comment!} Mapper 接口
* </p>
*
* @author ${author}
* @since ${date}
*/
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

     /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
     ${entity?replace('Entity','')}QueryVo get${table.entityName?replace('Entity','')}ById(Serializable id);

     /**
     * 获取分页对象
     *
     * @param page
     * @param queryParam
     * @return
     */
     IPage<${entity?replace('Entity','')}QueryVo> get${table.entityName?replace('Entity','')}PageList(@Param("page") Page page, @Param("param") PageModel queryParam);
}

