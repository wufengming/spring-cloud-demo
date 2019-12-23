package ${package.Service};

import com.wfm.servicecommon.vo.Paging;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${package.Entity?replace('.entity','')}.model.base.PageModel;
import ${package.Entity?replace('.entity','')}.model.vo.${entity?replace('Entity','')?lower_case}.${entity?replace('Entity','')}QueryVo;

import java.io.Serializable;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${table.entityName}> {

    /**
    * 保存
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean save${table.entityName?replace('Entity','')}(${table.entityName} entity) throws Exception;

    /**
    * 修改
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean update${table.entityName?replace('Entity','')}(${table.entityName} entity) throws Exception;

    /**
    * 删除
    *
    * @param id
    * @return
    * @throws Exception
    */
    boolean delete${table.entityName?replace('Entity','')}(Long id) throws Exception;

    /**
    * 根据ID获取查询对象
    *
    * @param id
    * @return
    * @throws Exception
    */
    ${entity?replace('Entity','')}QueryVo get${table.entityName?replace('Entity','')}ById(Serializable id) throws Exception;

    /**
    * 获取分页对象
    *
    * @param queryParam
    * @return
    * @throws Exception
    */
    Paging<${entity?replace('Entity','')}QueryVo> get${table.entityName?replace('Entity','')}PageList(PageModel queryParam) throws Exception;

}

