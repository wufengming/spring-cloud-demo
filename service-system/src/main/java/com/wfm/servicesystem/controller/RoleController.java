package com.wfm.servicesystem.controller;


import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.RoleEntity;
import com.wfm.servicesystem.model.vo.role.RoleQueryParam;
import com.wfm.servicesystem.model.vo.role.RoleQueryVo;
import com.wfm.servicesystem.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api("系统角色API")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 添加系统角色
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加Role对象", notes = "添加系统角色", response = ApiResult.class)
    public ApiResult<Boolean> addSysRole(@Valid @RequestBody RoleQueryVo roleQueryVo) throws Exception {
        boolean flag = roleService.saveSysRole(roleQueryVo);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统角色
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysRole对象", notes = "修改系统角色", response = ApiResult.class)
    public ApiResult<Boolean> updateSysRole(@Valid @RequestBody RoleQueryVo roleQueryVo) throws Exception {
        boolean flag = roleService.updateSysRole(roleQueryVo);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统角色
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除SysRole对象", notes = "删除系统角色", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysRole(@PathVariable("id") Long id) throws Exception {
        boolean flag = roleService.deleteSysRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统角色
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysRole对象详情", notes = "查看系统角色", response = RoleQueryVo.class)
    public ApiResult<RoleQueryVo> getSysRole(@PathVariable("id") Long id) throws Exception {
        RoleQueryVo sysRoleQueryVo = roleService.getSysRoleById(id);
        return ApiResult.ok(sysRoleQueryVo);
    }

    /**
     * 系统角色分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysRole分页列表", notes = "系统角色分页列表", response = RoleQueryVo.class)
    public ApiResult<Paging<RoleQueryVo>> getSysRolePageList(@Valid @RequestBody RoleQueryParam sysRoleQueryParam) throws Exception {
        Paging<RoleQueryVo> paging = roleService.getSysRolePageList(sysRoleQueryParam);
        return ApiResult.ok(paging);
    }
}

