package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicesystem.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.vo.role.RoleQueryParam;
import com.wfm.servicesystem.model.vo.role.RoleQueryVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {
    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    RoleQueryVo getSysRoleById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysRoleQueryParam
     * @return
     */
    IPage<RoleQueryVo> getSysRolePageList(@Param("page") Page page, @Param("param") RoleQueryParam sysRoleQueryParam);

}
