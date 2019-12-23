package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.RoleEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.vo.role.RoleQueryParam;
import com.wfm.servicesystem.model.vo.role.RoleQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface RoleService extends BaseService<RoleEntity> {
    /**
     * 保存
     *
     * @param roleQueryVo
     * @return
     * @throws Exception
     */
    boolean saveSysRole(RoleQueryVo roleQueryVo) throws Exception;

    /**
     * 修改
     *
     * @param roleQueryVo
     * @return
     * @throws Exception
     */
    boolean updateSysRole(RoleQueryVo roleQueryVo) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRole(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    RoleQueryVo getSysRoleById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param roleQueryParam
     * @return
     * @throws Exception
     */
    Paging<RoleQueryVo> getSysRolePageList(RoleQueryParam roleQueryParam) throws Exception;

    /**
     * 根据id校验角色是否存在并且已启用
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean isEnableSysRole(Long id) throws Exception;

    /**
     * 判断角色编码是否存在
     * @param code
     * @return
     * @throws Exception
     */
    boolean isExistsByCode(String code) throws Exception;
}
