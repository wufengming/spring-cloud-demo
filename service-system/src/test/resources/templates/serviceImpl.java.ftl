package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import ${package.Entity?replace('.entity','')}.model.base.PageModel;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${package.Entity?replace('.entity','')}.model.vo.${entity?replace('Entity','')?lower_case}.${entity?replace('Entity','')}QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save${table.entityName?replace('Entity','')}(${entity} entity) throws Exception {
        // 校验新增的条件 .....

        entity.setId(null);
        boolean saveResult = super.save(entity);
        if (!saveResult) {
            throw new DaoException("新增失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update${table.entityName?replace('Entity','')}(${entity} entity) throws Exception {
        // 校验修改的条件 .....

        boolean updateResult = updateById(entity);
        if (!updateResult) {
            throw new DaoException("修改失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete${table.entityName?replace('Entity','')}(Long id) throws Exception {

        // 删除条件判断 .....

        boolean deleteResult = removeById(id);
        if (!deleteResult) {
            throw new DaoException("删除失败");
        }

        return true;
    }

    @Override
    public ${entity?replace('Entity','')}QueryVo get${table.entityName?replace('Entity','')}ById(Serializable id) throws Exception {
        return ${table.mapperName?uncap_first}.get${table.entityName?replace('Entity','')}ById(id);
    }

    @Override
    public Paging<${entity?replace('Entity','')}QueryVo> get${table.entityName?replace('Entity','')}PageList(PageModel queryParam) throws Exception {
        Page page = setPageParam(queryParam, OrderItem.desc("update_dt"));
        IPage<${entity?replace('Entity','')}QueryVo> iPage = ${table.mapperName?uncap_first}.get${table.entityName?replace('Entity','')}PageList(page, queryParam);
        return new Paging(iPage);
    }

}

