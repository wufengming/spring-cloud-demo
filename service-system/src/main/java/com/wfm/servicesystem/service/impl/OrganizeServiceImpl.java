package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.enums.EnableStateEnum;
import com.wfm.servicesystem.entity.OrganizeEntity;
import com.wfm.servicesystem.entity.UserOrgEntity;
import com.wfm.servicesystem.mapper.OrganizeMapper;
import com.wfm.servicesystem.mapper.UserOrgMapper;
import com.wfm.servicesystem.model.convert.OrganizeConvert;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryParam;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryVo;
import com.wfm.servicesystem.model.vo.organize.OrganizeTreeVo;
import com.wfm.servicesystem.service.OrganizeService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Service
public class OrganizeServiceImpl extends BaseServiceImpl<OrganizeMapper, OrganizeEntity> implements OrganizeService {

    @Autowired
    private OrganizeMapper organizeMapper;

    @Autowired
    private UserOrgMapper userOrgMapper;

    /**
     * 新增组织信息
     * @param addOrganize
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysOrganize(OrganizeQueryVo addOrganize) throws Exception {
        // 校验当前的组织信息
        boolean isExists = isExistsByOrgCode(addOrganize.getEncode());
        if (isExists) {
            throw new BusinessException("当前组织已存在");
        }


        // 保存组织
        //DTO对象转换为实体对象
        OrganizeEntity sysOrganize = OrganizeConvert.INSTANCE.VoToOrganize(addOrganize);
        sysOrganize.setId(null);
        return super.save(sysOrganize);
    }

    /**
     * 更新组织
     * @param updateOrganizeVo
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysOrganize(OrganizeQueryVo updateOrganizeVo) throws Exception {

        //校验当前的组织信息
        OrganizeEntity updateSysOrg = getById(updateOrganizeVo.getId());
        if (updateSysOrg == null) {
            throw new BusinessException("当前修改组织不存在");
        }

        // 修改用户
        OrganizeEntity updateOrganize=OrganizeConvert.INSTANCE.VoToOrganize(updateOrganizeVo);

        return super.updateById(updateOrganize);
    }

    /**
     * 删除组织
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysOrganize(Long id) throws Exception {

        // 判断该组织下是否有可用用户，如果有，则不能删除
        boolean isExistsUser = userOrgMapper.selectCount(new LambdaQueryWrapper<UserOrgEntity>().eq(UserOrgEntity::getOrgid,id))>0;
        if (isExistsUser) {
            throw new DaoException("该组织下还存在可用用户，不能删除");
        }

        return super.removeById(id);
    }

    /**
     * 根据组织id获取组织信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public OrganizeQueryVo getSysOrganizeById(Serializable id) throws Exception {
        return organizeMapper.getSysOrganizeById(id);
    }

    /**
     * 分页获取组织全部集合
     * @param sysDepartmentQueryParam
     * @return
     * @throws Exception
     */
    @Override
    public Paging<OrganizeQueryVo> getSysOrganizePageList(OrganizeQueryParam sysDepartmentQueryParam) throws Exception {
        // 分页 排序，基本参数信息
        Page page = setPageParam(sysDepartmentQueryParam, OrderItem.desc("update_dt"));

        IPage<OrganizeQueryVo> iPage = organizeMapper.getSysOrganizePageList(page, sysDepartmentQueryParam);
        return new Paging(iPage);
    }

    /**
     *根据id校验组织是否存在并且已启用
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean isEnableSysOrganize(Long id) throws Exception {
        OrganizeEntity sysOrg = new OrganizeEntity();
        sysOrg.setId(id);
        sysOrg.setEnabledmark(EnableStateEnum.ENABLE.getCode());

        return organizeMapper.selectCount(new QueryWrapper<>(sysOrg)) > 0;
    }

    /**
     *根据组织code判断是否存在当前组织
     * @param code
     * @return
     */
    @Override
    public boolean isExistsByOrgCode(String code){
        OrganizeEntity organizeEntity=new OrganizeEntity();
        organizeEntity.setEncode(code);

        return organizeMapper.selectCount(new QueryWrapper<>(organizeEntity))>0;
    }

    /**
     * 获取所有可用的组织列表
     * @return
     */
    @Override
    public List<OrganizeEntity> getAllSysOrganizeList() {
        //OrganizeEntity sysOrg = new OrganizeEntity().setEnabledmark(EnableStateEnum.ENABLE.getCode());

        // 获取所有已启用的部门列表
        return organizeMapper.selectList(new LambdaQueryWrapper<OrganizeEntity>().eq(OrganizeEntity::getEnabledmark,EnableStateEnum.ENABLE.getCode()));
    }

    /**
     * 获取所有可用的组织树形列表
     * @return
     */
    @Override
    public List<OrganizeTreeVo> getAllSysOrganizeTree() {
        List<OrganizeEntity> sysOrganizeList = getAllSysOrganizeList();

        if (CollectionUtils.isEmpty(sysOrganizeList)) {
            throw new IllegalArgumentException("组织列表启用状态下数据为空");
        }
        List<OrganizeTreeVo> list = OrganizeConvert.INSTANCE.listToTreeVoList(sysOrganizeList);

        List<OrganizeTreeVo> treeVos = new ArrayList<>();
        for (OrganizeTreeVo treeVo : list) {
            if (treeVo.getParentId() == 0) {
                treeVos.add(findChildren(treeVo, list));
            }
        }
        return treeVos;
    }


    /**
     * 获取所有可用的组织树形列表 mybatis的递归查询collection
     * @return
     */
    @Override
    public List<OrganizeTreeVo> getAllSysOrganizeTree2() {
        return organizeMapper.getOrganizeTreeList(0);
    }

    /**
     * 递归获取树形结果列表
     *
     * @param tree
     * @param list
     * @return
     */
    public OrganizeTreeVo findChildren(OrganizeTreeVo tree, List<OrganizeTreeVo> list) {
        for (OrganizeTreeVo vo : list) {
            if (tree.getId().equals(vo.getParentId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(vo, list));
            }
        }
        return tree;
    }
}
