package com.wfm.servicesystem.controller;

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.DictEntity;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.dict.DictQueryVo;
import com.wfm.servicesystem.service.DictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 字典类型 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@RestController
@RequestMapping("/dict")
@Api(value = "字典类型 API")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加addDict对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addDict(@Valid @RequestBody DictEntity entity) throws Exception {
        boolean flag = dictService.saveDict(entity);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改updateDict对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateDict(@Valid @RequestBody DictEntity entity) throws Exception {
        boolean flag = dictService.updateDict(entity);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除deleteDict 对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteDict(@PathVariable("id") Long id) throws Exception {
        boolean flag = dictService.deleteDict(id);
        return ApiResult.result(flag);
    }

    /**
    * 根据主键获取数据
    */
    @GetMapping("/getDictInfo/{id}")
    @ApiOperation(value = "获取getDictModel 对象详情", notes = "查看", response = DictQueryVo.class)
    public ApiResult<DictQueryVo> getDictInfo(@PathVariable("id") Long id) throws Exception {
        DictQueryVo model = dictService.getDictById(id);
        return ApiResult.ok(model);
    }

    /**
    * 获取分页列表
    */
    @PostMapping("/getDictPageList")
    @ApiOperation(value = "获取getDictList 分页列表", notes = "分页列表", response = DictQueryVo.class)
    public ApiResult<Paging<DictQueryVo>> getDictPageList(@Valid @RequestBody PageModel queryParam) throws Exception {
        Paging<DictQueryVo> pageList = dictService.getDictPageList(queryParam);
        return ApiResult.ok(pageList);
    }
}

