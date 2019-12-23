package com.wfm.servicesystem.controller;

import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.MenuButtonEntity;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.menubutton.MenuButtonQueryVo;
import com.wfm.servicesystem.service.MenuButtonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 菜单按钮 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@RestController
@RequestMapping("/menuButton")
@Api(value = "菜单按钮 API")
public class MenuButtonController extends BaseController {

    @Autowired
    private MenuButtonService menuButtonService;

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加addMenuButton对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addMenuButton(@Valid @RequestBody MenuButtonEntity entity) throws Exception {
        boolean flag = menuButtonService.saveMenuButton(entity);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改updateMenuButton对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateMenuButton(@Valid @RequestBody MenuButtonEntity entity) throws Exception {
        boolean flag = menuButtonService.updateMenuButton(entity);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除deleteMenuButton 对象", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteMenuButton(@PathVariable("id") Long id) throws Exception {
        boolean flag = menuButtonService.deleteMenuButton(id);
        return ApiResult.result(flag);
    }

    /**
    * 根据主键获取数据
    */
    @GetMapping("/getMenuButtonInfo/{id}")
    @ApiOperation(value = "获取getMenuButtonModel 对象详情", notes = "查看", response = MenuButtonQueryVo.class)
    public ApiResult<MenuButtonQueryVo> getMenuButtonInfo(@PathVariable("id") Long id) throws Exception {
        MenuButtonQueryVo model = menuButtonService.getMenuButtonById(id);
        return ApiResult.ok(model);
    }

    /**
    * 获取分页列表
    */
    @PostMapping("/getMenuButtonPageList")
    @ApiOperation(value = "获取getMenuButtonList 分页列表", notes = "分页列表", response = MenuButtonQueryVo.class)
    public ApiResult<Paging<MenuButtonQueryVo>> getMenuButtonPageList(@Valid @RequestBody PageModel queryParam) throws Exception {
        Paging<MenuButtonQueryVo> pageList = menuButtonService.getMenuButtonPageList(queryParam);
        return ApiResult.ok(pageList);
    }
}

