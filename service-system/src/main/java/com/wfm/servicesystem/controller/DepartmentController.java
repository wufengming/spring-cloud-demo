package com.wfm.servicesystem.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.wfm.servicesystem.common.base.BaseController;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Slf4j
@RestController
@RequestMapping("/department")
@Api("组织部门 API")
public class DepartmentController extends BaseController {

}

