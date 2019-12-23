package ${package.Controller};

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import ${package.Entity}.${entity};
import ${package.Entity?replace('.entity','')}.model.base.PageModel;
import ${package.Entity?replace('.entity','')}.model.vo.${entity?replace('Entity','')?lower_case}.${entity?replace('Entity','')}QueryVo;
import ${package.Service}.${table.serviceName};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ${superControllerClassPackage};

import javax.validation.Valid;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.entityName?replace('Entity','')?uncap_first}")
@Api(value = "${table.comment!} API")
public class ${table.controllerName} extends ${superControllerClass} {

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加add${table.entityName?replace('Entity','')}对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> add${table.entityName?replace('Entity','')}(@Valid @RequestBody ${table.entityName} entity) throws Exception {
        boolean flag = ${table.serviceName?uncap_first}.save${table.entityName?replace('Entity','')}(entity);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改update${table.entityName?replace('Entity','')}对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> update${table.entityName?replace('Entity','')}(@Valid @RequestBody ${table.entityName} entity) throws Exception {
        boolean flag = ${table.serviceName?uncap_first}.update${table.entityName?replace('Entity','')}(entity);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除delete${table.entityName?replace('Entity','')} 对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> delete${table.entityName?replace('Entity','')}(@PathVariable("id") Long id) throws Exception {
        boolean flag = ${table.serviceName?uncap_first}.delete${table.entityName?replace('Entity','')}(id);
        return ApiResult.result(flag);
    }

    /**
    * 根据主键获取数据
    */
    @GetMapping("/get${table.entityName?replace('Entity','')}Info/{id}")
    @ApiOperation(value = "获取get${table.entityName?replace('Entity','')}Model 对象详情", notes = "查看", response = ${entity?replace('Entity','')}QueryVo.class)
    public ApiResult<${entity?replace('Entity','')}QueryVo> get${table.entityName?replace('Entity','')}Info(@PathVariable("id") Long id) throws Exception {
        ${entity?replace('Entity','')}QueryVo model = ${table.serviceName?uncap_first}.get${table.entityName?replace('Entity','')}ById(id);
        return ApiResult.ok(model);
    }

    /**
    * 获取分页列表
    */
    @PostMapping("/get${table.entityName?replace('Entity','')}PageList")
    @ApiOperation(value = "获取get${table.entityName?replace('Entity','')}List 分页列表", notes = "分页列表", response = ${entity?replace('Entity','')}QueryVo.class)
    public ApiResult<Paging<${entity?replace('Entity','')}QueryVo>> get${table.entityName?replace('Entity','')}PageList(@Valid @RequestBody PageModel queryParam) throws Exception {
        Paging<${entity?replace('Entity','')}QueryVo> pageList = ${table.serviceName?uncap_first}.get${table.entityName?replace('Entity','')}PageList(queryParam);
        return ApiResult.ok(pageList);
    }
}

