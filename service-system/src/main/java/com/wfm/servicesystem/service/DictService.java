package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.DictEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.dict.DictQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 字典类型 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
public interface DictService extends BaseService<DictEntity> {

    /**
    * 保存
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean saveDict(DictEntity entity) throws Exception;

    /**
    * 修改
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean updateDict(DictEntity entity) throws Exception;

    /**
    * 删除
    *
    * @param id
    * @return
    * @throws Exception
    */
    boolean deleteDict(Long id) throws Exception;

    /**
    * 根据ID获取查询对象
    *
    * @param id
    * @return
    * @throws Exception
    */
    DictQueryVo getDictById(Serializable id) throws Exception;

    /**
    * 获取分页对象
    *
    * @param queryParam
    * @return
    * @throws Exception
    */
    Paging<DictQueryVo> getDictPageList(PageModel queryParam) throws Exception;

}

