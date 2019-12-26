package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.base.InfoBaseModel;
import com.wfm.servicesystem.model.vo.user.UpdatePasswordParam;
import com.wfm.servicesystem.model.vo.user.UserQueryParam;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface UserService extends BaseService<UserEntity> {
    /**
     * 保存
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    boolean saveSysUser(UserQueryVo sysUser) throws Exception;

    /**
     * 修改
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    boolean updateSysUser(UserQueryVo sysUser) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysUser(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserQueryVo getSysUserById(InfoBaseModel id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysUserQueryParam
     * @return
     * @throws Exception
     */
    Paging<UserQueryVo> getSysUserPageList(UserQueryParam sysUserQueryParam) throws Exception;

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     * @throws Exception
     */
    boolean isExistsByUsername(String username) throws Exception;

    /**
     * 根据用户名称获取用户信息
     * @param username
     * @return
     * @throws Exception
     */
    UserEntity getUserByName(String username) throws Exception;

    /**
     * 根据用户名称获取用户信息
     * @param userName
     * @return
     */
    UserEntity findByUserName(String userName);

    /**
     * 修改密码
     *
     * @param updatePasswordParam
     * @return
     * @throws Exception
     */
    boolean updatePassword(UpdatePasswordParam updatePasswordParam) throws Exception;

    /**
     * 修改系统用户头像
     *
     * @param infoBaseModel
     * @param headPath
     * @return
     * @throws Exception
     */
    boolean updateSysUserHead(InfoBaseModel infoBaseModel, String headPath) throws Exception;

    /**
     * 测试切换数据库的测试例子
     * @param id
     * @return
     */
    UserEntity queryUserById(Long id);
}
