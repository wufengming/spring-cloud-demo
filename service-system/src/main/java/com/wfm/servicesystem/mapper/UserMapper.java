package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicesystem.entity.OrganizeEntity;
import com.wfm.servicesystem.entity.RoleEntity;
import com.wfm.servicesystem.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.vo.login.PermissionMenu;
import com.wfm.servicesystem.model.vo.user.UserQueryParam;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 根据ID获取查询对象
     *
     * @param userid
     * @param orgid
     * @return
     */
    UserQueryVo getSysUserById(@Param("userid") Long userid,@Param("orgid") Long orgid);

    /**
     * 获取分页对象
     *
     * @param page
     * @param param
     * @return
     */
    IPage<UserQueryVo> getSysUserPageList(@Param("page") Page page, @Param("param") UserQueryParam param);


    /**
     * 获取用户的相关的组织信息
     * @param userid 用户主键
     * @return
     */
    List<OrganizeEntity> getOrganizeListByUser(@Param("userid") Long userid);

    /**
     * 获取用户相关的角色信息
     * @param userid 用户主键
     * @return
     */
    List<RoleEntity> getRoleListByUser(@Param("userid") Long userid);

    /**
     * 根据用户，获取当前用户的权限（菜单，按钮）
     * @param userid
     * @return
     */
    List<PermissionMenu> getPermissionByUser(@Param("userid") Long userid);
}
