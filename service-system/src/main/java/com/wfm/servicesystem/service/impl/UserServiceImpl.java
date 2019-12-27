package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.utils.CommonUtil;
import com.wfm.servicecommon.utils.PasswordUtil;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.config.datasource.EnumDataSourceType;
import com.wfm.servicesystem.config.datasource.TargetDataSource;
import com.wfm.servicesystem.model.enums.EnableStateEnum;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.entity.UserOrgEntity;
import com.wfm.servicesystem.mapper.UserMapper;
import com.wfm.servicesystem.model.base.InfoBaseModel;
import com.wfm.servicesystem.model.convert.UserConvert;
import com.wfm.servicesystem.model.vo.user.UpdatePasswordParam;
import com.wfm.servicesystem.model.vo.user.UserQueryParam;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import com.wfm.servicesystem.service.UserOrgService;
import com.wfm.servicesystem.service.UserService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOrgService userOrgService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysUser(UserQueryVo addUser) throws Exception {
        // 校验用户名是否存在
        boolean isExists = isExistsByUsername(addUser.getAccount());
        if (isExists) {
            throw new BusinessException("当前账号已存在");
        }

        // 保存角色
        //DTO对象转换为实体对象
        UserEntity sysUser = UserConvert.INSTANCE.VoToUser(addUser);

        // 用户，组织和角色
        List<UserOrgEntity> userOrgEntityList = UserConvert.INSTANCE.VoToUserOrg(addUser.getUserorgrolelist());

        if (userOrgEntityList == null || userOrgEntityList.size() == 0) {
            throw new BusinessException("当前用户对于的组织角色关系为空");
        }

        // uuid获取随机字符串，作为盐值。
        String salt = CommonUtil.getUUID();
        sysUser.setSalt(salt);

        // 密码加密
        String newPassword = PasswordUtil.encrypt(sysUser.getPassword(), salt);
        sysUser.setPassword(newPassword);

        // 保存系统用户
        super.save(sysUser);

        //方法一： 关系表的用户信息赋值
        //ForEach：返回void，即无返回值
        userOrgEntityList.forEach(a -> {
            a.setUserId(sysUser.getId());
            a.setUserAccount(sysUser.getAccount());
        });
        //方法三： 关系表的用户信息赋值
//        userOrgEntityList.stream().forEach(a -> {
//            a.setUserId(sysUser.getId());
//            a.setUserAccount(sysUser.getAccount());
//        });

        //方法二： 关系表的用户信息赋值
        //Map：返回的是一个新流，可以对这个流进一步操作
//        List<UserOrgEntity> userOrgEntityList2 = userOrgEntityList.stream().map(a -> {
//            a.setUserId(sysUser.getId());
//            a.setUserAccount(sysUser.getAccount());
//            return a;
//        }).collect(Collectors.toList());

        //保存用户，组织，角色的关系信息
        userOrgService.saveBatch(userOrgEntityList);


        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(UserQueryVo updateUser) throws Exception {
        // 校验部门和角色
        //checkDepartmentAndRole(sysUser.getDepartmentId(), sysUser.getRoleId());
        // 获取系统用户
        UserEntity updateSysUser = getById(updateUser.getId());
        if (updateSysUser == null) {
            throw new BusinessException("当前修改用户不存在");
        }

        // 修改用户
        UserEntity sysUser = UserConvert.INSTANCE.VoToUser(updateUser);

        // 用户，组织和角色
        List<UserOrgEntity> userelatList = UserConvert.INSTANCE.VoToUserOrg(updateUser.getUserorgrolelist());

        if(userelatList==null || userelatList.size()==0) throw new BusinessException("当前用户对于的组织角色关系为空");



        super.updateById(sysUser);

        //关系表的用户信息赋值
        // 1.删除原先的关系，重写写入关系信息

        //删除关系.同一个用户，同一个组织的角色关系删除

        userOrgService.remove(new LambdaQueryWrapper<UserOrgEntity>()
                .eq(UserOrgEntity::getUserId, sysUser.getId())
                .eq(UserOrgEntity::getOrgid, userelatList.get(0).getOrgid()));
        //保存关系
        userOrgService.saveBatch(userelatList);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {

        //删除用户主表内容
        super.removeById(id);

        //删除用户关系表的内容
        userOrgService.remove(new LambdaQueryWrapper<UserOrgEntity>().eq(UserOrgEntity::getUserId, id));


        return true;
    }

    @Override
    public UserQueryVo getSysUserById(InfoBaseModel infoModel) throws Exception {


        return userMapper.getSysUserById(infoModel.getUserId(),infoModel.getOrgId());
    }

    @Override
    public Paging<UserQueryVo> getSysUserPageList(UserQueryParam userQueryParam) throws Exception {
        Page page = setPageParam(userQueryParam, OrderItem.desc("update_dt"));
        IPage<UserQueryVo> iPage = userMapper.getSysUserPageList(page, userQueryParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isExistsByUsername(String username) throws Exception {
        UserEntity selectSysUser = new UserEntity();
        selectSysUser.setAccount(username);
        return userMapper.selectCount(new QueryWrapper<>(selectSysUser)) > 0;
    }

    @Override
    public UserEntity getUserByName(String username) throws Exception{

        return userMapper.selectOne(new LambdaQueryWrapper<UserEntity>()
                // 用户名称
                .eq(UserEntity::getAccount,username));
    }

    @Override
    public UserEntity findByUserName(String userName) {

        return userMapper.selectOne(new LambdaQueryWrapper<UserEntity>()
                // 用户名称
                .eq(UserEntity::getAccount,userName));
    }


    @Override
    public boolean updatePassword(UpdatePasswordParam updatePasswordParam) throws Exception {
        //m密码修改的参数
        String oldPassword = updatePasswordParam.getOldPassword();
        String newPassword = updatePasswordParam.getNewPassword();
        String confirmPassword = updatePasswordParam.getConfirmPassword();

        if (!newPassword.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }
        if (newPassword.equals(oldPassword)) {
            throw new BusinessException("新密码和旧密码不能一致");
        }

        // 判断原密码是否正确
        UserEntity sysUser = getById(updatePasswordParam.getUserId());
        if (sysUser == null) {
            throw new BusinessException("修改用户不存在");
        }
        if (EnableStateEnum.DISABLE.getCode().equals(sysUser.getEnabledmark())) {
            throw new BusinessException("用户已禁用");
        }
        // 密码加密处理
        String salt = sysUser.getSalt();
        String encryptOldPassword = PasswordUtil.encrypt(oldPassword, salt);
        if (!sysUser.getPassword().equals(encryptOldPassword)) {
            throw new BusinessException("原密码错误");
        }
        // 新密码加密
        String encryptNewPassword = PasswordUtil.encrypt(newPassword, salt);

        // 修改密码
        sysUser.setPassword(encryptNewPassword);

        return super.updateById(sysUser);
    }

    @Override
    public boolean updateSysUserHead(InfoBaseModel infoBaseModel, String headPath) throws Exception {
        UserEntity updateUser = new UserEntity();

        UserEntity sysUser = getById(infoBaseModel.getId());
        if (sysUser == null) {
            throw new BusinessException("修改用户不存在");
        }

        updateUser.setHeadicon(headPath)
                  .setId(sysUser.getId())
                  .setRecordVer(sysUser.getRecordVer());
        return updateById(updateUser);
    }

    @Override
    @TargetDataSource(EnumDataSourceType.SLAVE1)
    public UserEntity queryUserById(Long id) {
        return userMapper.selectById(id);
    }
}
