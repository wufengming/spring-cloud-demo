package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.DictDataEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.dictdata.DictDataQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 字典数据 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
public interface DictDataService extends BaseService<DictDataEntity> {

    /**
    * 保存
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean saveDictData(DictDataEntity entity) throws Exception;

    /**
    * 修改
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean updateDictData(DictDataEntity entity) throws Exception;

    /**
    * 删除
    *
    * @param id
    * @return
    * @throws Exception
    */
    boolean deleteDictData(Long id) throws Exception;

    /**
    * 根据ID获取查询对象
    *
    * @param id
    * @return
    * @throws Exception
    */
    DictDataQueryVo getDictDataById(Serializable id) throws Exception;

    /**
    * 获取分页对象
    *
    * @param queryParam
    * @return
    * @throws Exception
    */
    Paging<DictDataQueryVo> getDictDataPageList(PageModel queryParam) throws Exception;

}

