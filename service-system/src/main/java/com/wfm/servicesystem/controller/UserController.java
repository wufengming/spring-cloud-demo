package com.wfm.servicesystem.controller;


import com.wfm.servicecommon.api.ApiResult;
import com.wfm.servicecommon.aspect.AutoLog;
import com.wfm.servicecommon.constant.CommonConstant;
import com.wfm.servicecommon.utils.CommonUtil;
import com.wfm.servicecommon.utils.UploadUtil;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.common.properties.SystemProperties;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.model.base.InfoBaseModel;
import com.wfm.servicesystem.model.vo.user.UpdatePasswordParam;
import com.wfm.servicesystem.model.vo.user.UserQueryParam;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import com.wfm.servicesystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wfm.servicesystem.common.base.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api("系统用户 API")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 添加系统用户
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加User对象", notes = "添加系统用户", response = ApiResult.class)
    @AutoLog(value = "新增user")
    public ApiResult<Boolean> addSysUser(@Valid @RequestBody UserQueryVo sysUser) throws Exception {
        boolean flag = userService.saveSysUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改User对象", notes = "修改系统用户", response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Valid @RequestBody UserQueryVo sysUser) throws Exception {
        boolean flag = userService.updateSysUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统用户
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除User对象", notes = "删除系统用户", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@PathVariable("id") Long id) throws Exception {
        if(CommonUtil.isEmpty(id)){
            return ApiResult.fail("主键为空，请确认单据！");
        }
        boolean flag = userService.deleteSysUser(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统用户
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取User对象详情", notes = "查看系统用户", response = UserQueryVo.class)
    public ApiResult<UserQueryVo> getSysUser(@Valid @RequestBody InfoBaseModel infoModel) throws Exception {
        if(CommonUtil.isEmpty(infoModel.getUserId()) || CommonUtil.isEmpty(infoModel.getOrgId())){
            return ApiResult.fail("用户主键或者组织主键为空，请确认单据信息！");
        }

        UserQueryVo sysUserQueryVo = userService.getSysUserById(infoModel);
        return ApiResult.ok(sysUserQueryVo);
    }

    /**
     * 系统用户分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取User分页列表", notes = "系统用户分页列表", response = UserQueryVo.class)
    public ApiResult<Paging<UserQueryVo>> getSysUserPageList(@Valid @RequestBody UserQueryParam userQueryParam) throws Exception {
        Paging<UserQueryVo> paging = userService.getSysUserPageList(userQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码", notes = "修改密码", response = ApiResult.class)
    public ApiResult<Boolean> updatePassword(@Valid @RequestBody UpdatePasswordParam updatePasswordParam) throws Exception {
        boolean flag = userService.updatePassword(updatePasswordParam);
        return ApiResult.result(flag);
    }

    /**
     * 修改头像
     *
     */
    @PostMapping("/uploadFile")
    @ApiOperation(value = "修改头像", notes = "修改头像", response = ApiResult.class)
    public ApiResult<Boolean> uploadFile(@ModelAttribute InfoBaseModel infoBaseModel,
                                         @RequestParam("file") MultipartFile multipartFile) throws Exception {


        String fileName = multipartFile.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String contentType=multipartFile.getContentType(); // 类型
        Long size=multipartFile.getSize(); // 文件大小

        // 相对路径 resources
        String uploadPath = systemProperties.getUploadPath();

        //String basePath = request.getServletContext().getRealPath(uploadPath);


        //上传后的文件名称
        String saveFileName = UploadUtil.upload(uploadPath, multipartFile);
        // 上传成功之后，返回访问路径，请根据实际情况设置
        String headPath = systemProperties.getResourceAccessUrl() + saveFileName;

        boolean flag = userService.updateSysUserHead(infoBaseModel, headPath);
        if (flag) {
            return ApiResult.ok(headPath);
        }

        // 删除图片文件
        UploadUtil.deleteQuietly(uploadPath, saveFileName);
        return ApiResult.fail();
    }
}

