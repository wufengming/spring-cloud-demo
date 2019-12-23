package com.wfm.servicesystem.model.convert;

import com.wfm.servicesystem.entity.RoleAuthorizeEntity;
import com.wfm.servicesystem.entity.RoleEntity;
import com.wfm.servicesystem.entity.UserOrgEntity;
import com.wfm.servicesystem.model.vo.role.RoleAuthorizeVo;
import com.wfm.servicesystem.model.vo.role.RoleQueryVo;
import com.wfm.servicesystem.model.vo.user.UserOrgRoleVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * description: 系统角色转换器
 * date: 2019-11-08 12:40
 * author: wfm
 * version: 1.0
 */
@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);
    /**
     * Vo转RoleEntity
     *
     * @param roleQueryVo
     * @return
     */
    RoleEntity VoToRole(RoleQueryVo roleQueryVo);

    /**
     * 获取用户组织角色的关系
     * @param list
     * @return
     */
    List<RoleAuthorizeEntity> VoToRoleAuthorize(List<RoleAuthorizeVo> list);
}
