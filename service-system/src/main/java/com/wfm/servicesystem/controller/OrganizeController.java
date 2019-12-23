package com.wfm.servicesystem.controller;


import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.OrganizeEntity;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryParam;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryVo;
import com.wfm.servicesystem.model.vo.organize.OrganizeTreeVo;
import com.wfm.servicesystem.service.OrganizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 组织表 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Slf4j
@RestController
@RequestMapping("/organize")
@Api("组织 API")
public class OrganizeController extends BaseController {

    @Autowired
    private OrganizeService organizeService;

    /**
     * 添加组织
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加Organize对象", notes = "添加组织", response = ApiResult.class)
    public ApiResult<Boolean> addSysOrganize(@Valid @RequestBody OrganizeQueryVo organizeQueryVo) throws Exception {
        boolean flag = organizeService.saveSysOrganize(organizeQueryVo);
        return ApiResult.result(flag);
    }

    /**
     * 修改组织
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改Organize对象", notes = "修改组织", response = ApiResult.class)
    public ApiResult<Boolean> updateSysOrganize(@Valid @RequestBody OrganizeQueryVo organizeQueryVo) throws Exception {
        boolean flag = organizeService.updateSysOrganize(organizeQueryVo);
        return ApiResult.result(flag);
    }

    /**
     * 删除组织
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除Organize对象", notes = "删除组织", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysOrganize(@PathVariable("id") Long id) throws Exception {
        boolean flag = organizeService.deleteSysOrganize(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取组织
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysOrganize对象详情", notes = "查看组织", response = OrganizeQueryVo.class)
    public ApiResult<OrganizeQueryVo> getSysOrganize(@PathVariable("id") Long id) throws Exception {
        OrganizeQueryVo sysOrganizeQueryVo = organizeService.getSysOrganizeById(id);
        return ApiResult.ok(sysOrganizeQueryVo);
    }

    /**
     * 组织分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysOrganize分页列表", notes = "组织分页列表", response = OrganizeQueryVo.class)
    public ApiResult<Paging<OrganizeQueryVo>> getPageList(@Valid @RequestBody OrganizeQueryParam organizeQueryParam) throws Exception {
        Paging<OrganizeQueryVo> paging = organizeService.getSysOrganizePageList(organizeQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取所有组织列表
     */
    @PostMapping("/getOrgAllList")
    @ApiOperation(value = "获取所有组织的树形列表", notes = "获取所有组织的树形列表", response = OrganizeEntity.class)
    public ApiResult<Paging<OrganizeEntity>> getOrgAllList() throws Exception {
        List<OrganizeEntity> list = organizeService.getAllSysOrganizeList();
        return ApiResult.ok(list);
    }

    /**
     * 获取所有组织的树形列表
     */
    @PostMapping("/getOrgAllTree")
    @ApiOperation(value = "获取所有组织的树形列表", notes = "获取所有组织的树形列表", response = OrganizeTreeVo.class)
    public ApiResult<Paging<OrganizeTreeVo>> getOrgAllTree() throws Exception {
        List<OrganizeTreeVo> treeVos = organizeService.getAllSysOrganizeTree();
        return ApiResult.ok(treeVos);
    }

    /**
     * 获取所有组织的树形列表
     */
    @PostMapping("/getOrgAllTree2")
    @ApiOperation(value = "获取所有组织的树形列表", notes = "获取所有组织的树形列表", response = OrganizeTreeVo.class)
    public ApiResult<Paging<OrganizeTreeVo>> getOrgAllTree2() throws Exception {
        List<OrganizeTreeVo> treeVos = organizeService.getAllSysOrganizeTree2();
        return ApiResult.ok(treeVos);
    }

}


