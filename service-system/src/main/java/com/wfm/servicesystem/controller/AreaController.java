package com.wfm.servicesystem.controller;

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.AreaEntity;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.area.AreaQueryVo;
import com.wfm.servicesystem.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 行政区域表 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@RestController
@RequestMapping("/area")
@Api(value = "行政区域表 API")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加addArea对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addArea(@Valid @RequestBody AreaEntity entity) throws Exception {
        boolean flag = areaService.saveArea(entity);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改updateArea对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateArea(@Valid @RequestBody AreaEntity entity) throws Exception {
        boolean flag = areaService.updateArea(entity);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除deleteArea 对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteArea(@PathVariable("id") Long id) throws Exception {
        boolean flag = areaService.deleteArea(id);
        return ApiResult.result(flag);
    }

    /**
    * 根据主键获取数据
    */
    @GetMapping("/getAreaInfo/{id}")
    @ApiOperation(value = "获取getAreaModel 对象详情", notes = "查看", response = AreaQueryVo.class)
    public ApiResult<AreaQueryVo> getAreaInfo(@PathVariable("id") Long id) throws Exception {
        AreaQueryVo model = areaService.getAreaById(id);
        return ApiResult.ok(model);
    }

    /**
    * 获取分页列表
    */
    @PostMapping("/getAreaPageList")
    @ApiOperation(value = "获取getAreaList 分页列表", notes = "分页列表", response = AreaQueryVo.class)
    public ApiResult<Paging<AreaQueryVo>> getAreaPageList(@Valid @RequestBody PageModel queryParam) throws Exception {
        Paging<AreaQueryVo> pageList = areaService.getAreaPageList(queryParam);
        return ApiResult.ok(pageList);
    }
}

