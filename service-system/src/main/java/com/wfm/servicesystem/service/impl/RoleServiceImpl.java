package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.enums.EnableStateEnum;
import com.wfm.servicesystem.entity.RoleAuthorizeEntity;
import com.wfm.servicesystem.entity.RoleEntity;
import com.wfm.servicesystem.entity.UserOrgEntity;
import com.wfm.servicesystem.mapper.RoleMapper;
import com.wfm.servicesystem.mapper.UserOrgMapper;
import com.wfm.servicesystem.model.convert.RoleConvert;
import com.wfm.servicesystem.model.vo.role.RoleQueryParam;
import com.wfm.servicesystem.model.vo.role.RoleQueryVo;
import com.wfm.servicesystem.service.RoleAuthorizeService;
import com.wfm.servicesystem.service.RoleService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import com.wfm.servicesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleAuthorizeService roleAuthorizeService;

    @Autowired
    private UserOrgMapper userOrgMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRole(RoleQueryVo roleQueryVo) throws Exception {

        String code = roleQueryVo.getEncode();
        // 校验角色标识code唯一性
        if (isExistsByCode(code)) {
            throw new BusinessException("角色编码已存在");
        }

        // 保存角色
        //DTO对象转换为实体对象
        RoleEntity sysRole = RoleConvert.INSTANCE.VoToRole(roleQueryVo);

        //获取角色权限信息
        List<RoleAuthorizeEntity> roleAuthorizeEntityList=RoleConvert.INSTANCE.VoToRoleAuthorize(roleQueryVo.getRoleauthorizelist());

        sysRole.setId(null);
        boolean saveRoleResult = super.save(sysRole);
        if (!saveRoleResult) {
            throw new DaoException("保存角色失败");
        }

        // 保存角色权限
        roleAuthorizeEntityList.forEach(a -> {
            a.setObjectId(sysRole.getId());
            a.setObjecttype(1);
        });

         boolean saveRolePermissionResult = roleAuthorizeService.saveBatch(roleAuthorizeEntityList);
         if (!saveRolePermissionResult) {
             throw new DaoException("保存角色权限失败");
         }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(RoleQueryVo updateRoleVo) throws Exception {

        // 校验角色是否存在
        RoleEntity updateRole = getById(updateRoleVo.getId());
        if (updateRole == null) {
            throw new BusinessException("该角色不存在");
        }

        //DTO对象转换为实体对象
        RoleEntity sysRole = RoleConvert.INSTANCE.VoToRole(updateRoleVo);

        //获取角色权限信息
        List<RoleAuthorizeEntity> roleAuthorizeEntityList=RoleConvert.INSTANCE.VoToRoleAuthorize(updateRoleVo.getRoleauthorizelist());

        boolean updateResult = updateById(sysRole);
        if (!updateResult) {
            throw new DaoException("修改系统角色失败");
        }



        // 删除权限关联
        boolean deleteResult = roleAuthorizeService.remove(new LambdaUpdateWrapper<RoleAuthorizeEntity>()
                .eq(RoleAuthorizeEntity::getObjectId,updateRole.getId())
                .eq(RoleAuthorizeEntity::getObjecttype,1));
        if (!deleteResult) {
            throw new DaoException("删除角色权限关系失败");
        }
        // 新增权限关联
        roleAuthorizeEntityList.forEach(a -> {
            a.setObjectId(sysRole.getId());
            a.setObjecttype(1);
        });
        boolean addResult = roleAuthorizeService.saveBatch(roleAuthorizeEntityList);
        if (!addResult) {
            throw new DaoException("新增角色权限关系失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Long id) throws Exception {

        // 判断该角色下是否有可用用户，如果有，则不能删除
        boolean isExistsUser = userOrgMapper.selectCount(new LambdaQueryWrapper<UserOrgEntity>().eq(UserOrgEntity::getRolePhid,id))>0;
        if (isExistsUser) {
            throw new DaoException("该角色下还存在可用用户，不能删除");
        }
        // 角色真实删除
        boolean deleteRoleResult = removeById(id);
        if (!deleteRoleResult) {
            throw new DaoException("删除角色失败");
        }
        // 角色权限关系真实删除
        boolean deletePermissionResult = roleAuthorizeService.remove(new LambdaQueryWrapper<RoleAuthorizeEntity>()
                .eq(RoleAuthorizeEntity::getObjectId,id)
                .eq(RoleAuthorizeEntity::getObjecttype,1));
        if (!deletePermissionResult) {
            throw new DaoException("删除角色权限关系失败");
        }
        return true;
    }

    @Override
    public RoleQueryVo getSysRoleById(Serializable id) throws Exception {
        return roleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<RoleQueryVo> getSysRolePageList(RoleQueryParam roleQueryParam) throws Exception {
        Page page = setPageParam(roleQueryParam, OrderItem.desc("update_dt"));
        IPage<RoleQueryVo> iPage = roleMapper.getSysRolePageList(page, roleQueryParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isEnableSysRole(Long id) throws Exception {
        RoleEntity sysRole = new RoleEntity();
        sysRole.setId(id);
        sysRole.setEnabledmark(EnableStateEnum.ENABLE.getCode());
        int count = roleMapper.selectCount(new QueryWrapper<>(sysRole));
        return count > 0;
    }

    @Override
    public boolean isExistsByCode(String code) throws Exception {
        RoleEntity sysRole = new RoleEntity().setEncode(code);
        return roleMapper.selectCount(new QueryWrapper<>(sysRole)) > 0;
    }
}
