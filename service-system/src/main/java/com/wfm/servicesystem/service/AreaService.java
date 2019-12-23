package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.AreaEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.area.AreaQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 行政区域表 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
public interface AreaService extends BaseService<AreaEntity> {

    /**
    * 保存
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean saveArea(AreaEntity entity) throws Exception;

    /**
    * 修改
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean updateArea(AreaEntity entity) throws Exception;

    /**
    * 删除
    *
    * @param id
    * @return
    * @throws Exception
    */
    boolean deleteArea(Long id) throws Exception;

    /**
    * 根据ID获取查询对象
    *
    * @param id
    * @return
    * @throws Exception
    */
    AreaQueryVo getAreaById(Serializable id) throws Exception;

    /**
    * 获取分页对象
    *
    * @param queryParam
    * @return
    * @throws Exception
    */
    Paging<AreaQueryVo> getAreaPageList(PageModel queryParam) throws Exception;

}

