package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicesystem.entity.AreaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.area.AreaQueryVo;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
* <p>
* 行政区域表 Mapper 接口
* </p>
*
* @author wfm
* @since 2019-12-09
*/
public interface AreaMapper extends BaseMapper<AreaEntity> {

     /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
     AreaQueryVo getAreaById(Serializable id);

     /**
     * 获取分页对象
     *
     * @param page
     * @param queryParam
     * @return
     */
     IPage<AreaQueryVo> getAreaPageList(@Param("page") Page page, @Param("param") PageModel queryParam);
}

