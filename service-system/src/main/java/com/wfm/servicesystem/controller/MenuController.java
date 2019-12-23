package com.wfm.servicesystem.controller;

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.MenuEntity;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.menu.MenuQueryVo;
import com.wfm.servicesystem.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 菜单模块 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(value = "菜单模块 API")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加addMenu对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addMenu(@Valid @RequestBody MenuEntity entity) throws Exception {
        boolean flag = menuService.saveMenu(entity);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改updateMenu对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateMenu(@Valid @RequestBody MenuEntity entity) throws Exception {
        boolean flag = menuService.updateMenu(entity);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除deleteMenu 对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteMenu(@PathVariable("id") Long id) throws Exception {
        boolean flag = menuService.deleteMenu(id);
        return ApiResult.result(flag);
    }

    /**
    * 根据主键获取数据
    */
    @GetMapping("/getMenuInfo/{id}")
    @ApiOperation(value = "获取getMenuModel 对象详情", notes = "查看", response = MenuQueryVo.class)
    public ApiResult<MenuQueryVo> getMenuInfo(@PathVariable("id") Long id) throws Exception {
        MenuQueryVo model = menuService.getMenuById(id);
        return ApiResult.ok(model);
    }

    /**
    * 获取分页列表
    */
    @PostMapping("/getMenuPageList")
    @ApiOperation(value = "获取getMenuList 分页列表", notes = "分页列表", response = MenuQueryVo.class)
    public ApiResult<Paging<MenuQueryVo>> getMenuPageList(@Valid @RequestBody PageModel queryParam) throws Exception {
        Paging<MenuQueryVo> pageList = menuService.getMenuPageList(queryParam);
        return ApiResult.ok(pageList);
    }
}

