package com.wfm.servicesystem.controller;

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.DictDataEntity;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.dictdata.DictDataQueryVo;
import com.wfm.servicesystem.service.DictDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 字典数据 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@RestController
@RequestMapping("/dictData")
@Api(value = "字典数据 API")
public class DictDataController extends BaseController {

    @Autowired
    private DictDataService dictDataService;

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加addDictData对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addDictData(@Valid @RequestBody DictDataEntity entity) throws Exception {
        boolean flag = dictDataService.saveDictData(entity);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改updateDictData对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateDictData(@Valid @RequestBody DictDataEntity entity) throws Exception {
        boolean flag = dictDataService.updateDictData(entity);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除deleteDictData 对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteDictData(@PathVariable("id") Long id) throws Exception {
        boolean flag = dictDataService.deleteDictData(id);
        return ApiResult.result(flag);
    }

    /**
    * 根据主键获取数据
    */
    @GetMapping("/getDictDataInfo/{id}")
    @ApiOperation(value = "获取getDictDataModel 对象详情", notes = "查看", response = DictDataQueryVo.class)
    public ApiResult<DictDataQueryVo> getDictDataInfo(@PathVariable("id") Long id) throws Exception {
        DictDataQueryVo model = dictDataService.getDictDataById(id);
        return ApiResult.ok(model);
    }

    /**
    * 获取分页列表
    */
    @PostMapping("/getDictDataPageList")
    @ApiOperation(value = "获取getDictDataList 分页列表", notes = "分页列表", response = DictDataQueryVo.class)
    public ApiResult<Paging<DictDataQueryVo>> getDictDataPageList(@Valid @RequestBody PageModel queryParam) throws Exception {
        Paging<DictDataQueryVo> pageList = dictDataService.getDictDataPageList(queryParam);
        return ApiResult.ok(pageList);
    }
}

