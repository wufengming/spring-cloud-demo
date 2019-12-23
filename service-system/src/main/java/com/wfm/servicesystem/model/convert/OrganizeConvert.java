package com.wfm.servicesystem.model.convert;

import com.wfm.servicesystem.entity.OrganizeEntity;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryVo;
import com.wfm.servicesystem.model.vo.organize.OrganizeTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * description: OrganizeConvert
 * date: 2019-11-25 20:00
 * author: wfm
 * version: 1.0
 */
@Mapper
public interface OrganizeConvert {

    OrganizeConvert INSTANCE= Mappers.getMapper(OrganizeConvert.class);

    /**
     * Vo转OrganizeEntity
     * @param organizeQueryVo 视图组织信息
     * @return 返回POJO组织信息
     */
    OrganizeEntity VoToOrganize(OrganizeQueryVo organizeQueryVo);

    /**
     * OrganizeEntity列表转换成OrganizeTreeVo列表
     *
     * @param list
     * @return
     */
//    @Mappings({
//            @Mapping(source="id", target="id"),
//            @Mapping(source="parentid", target="parentId"),
//            @Mapping(source="encode", target="code"),
//            @Mapping(source="name", target="name"),
//            @Mapping(source="enabledmark", target="state"),
//            @Mapping(source="sortOrder", target="sort"),
//            @Mapping(source="description", target="remark"),
//            @Mapping(source="recordVer", target="recordVer")
//    })
    List<OrganizeTreeVo> listToTreeVoList(List<OrganizeEntity> list);
}
