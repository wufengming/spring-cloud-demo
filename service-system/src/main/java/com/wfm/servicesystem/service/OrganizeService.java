package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.OrganizeEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryParam;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryVo;
import com.wfm.servicesystem.model.vo.organize.OrganizeTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 组织表 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface OrganizeService extends BaseService<OrganizeEntity> {

    /**
     * 保存
     * @param organizeQueryVo
     * @return
     * @throws Exception
     */
    boolean saveSysOrganize(OrganizeQueryVo organizeQueryVo) throws Exception;
    /**
     * 修改
     * @param organizeQueryVo
     * @return
     * @throws Exception
     */
    boolean updateSysOrganize(OrganizeQueryVo organizeQueryVo) throws Exception;

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysOrganize(Long id) throws Exception;
    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    OrganizeQueryVo getSysOrganizeById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param organizeQueryParam
     * @return
     * @throws Exception
     */
    Paging<OrganizeQueryVo> getSysOrganizePageList(OrganizeQueryParam organizeQueryParam) throws Exception;

    /**
     * 根据id校验组织是否存在并且已启用
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean isEnableSysOrganize(Long id) throws Exception;


    /**
     * 根据组织code判断是否存在当前组织
     * @param code
     * @return
     */
    boolean isExistsByOrgCode(String code);

    /**
     * 获取所有可用的组织列表
     * @return
     */
    List<OrganizeEntity> getAllSysOrganizeList();

    /**
     * 获取所有可用的组织树形列表 mybatis的递归查询collection
     * @return
     */
    List<OrganizeTreeVo> getAllSysOrganizeTree2();

    /**
     * 获取所有可用的组织树形列表
     * @return
     */
    List<OrganizeTreeVo> getAllSysOrganizeTree();



}
