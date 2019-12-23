package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicesystem.entity.OrganizeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryParam;
import com.wfm.servicesystem.model.vo.organize.OrganizeQueryVo;
import com.wfm.servicesystem.model.vo.organize.OrganizeTreeVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 组织表 Mapper 接口
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface OrganizeMapper extends BaseMapper<OrganizeEntity> {
    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    OrganizeQueryVo getSysOrganizeById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page 分页信息
     * @param param 查询条件
     * @return 分页的对象
     */
    IPage<OrganizeQueryVo> getSysOrganizePageList(@Param("page") Page page, @Param("param") OrganizeQueryParam param);

    List<OrganizeTreeVo> getOrganizeTreeList(@Param("id")Serializable id);

}
