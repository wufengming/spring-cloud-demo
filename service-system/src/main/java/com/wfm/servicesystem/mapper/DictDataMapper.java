package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicesystem.entity.DictDataEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.dictdata.DictDataQueryVo;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
* <p>
* 字典数据 Mapper 接口
* </p>
*
* @author wfm
* @since 2019-12-09
*/
public interface DictDataMapper extends BaseMapper<DictDataEntity> {

     /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
     DictDataQueryVo getDictDataById(Serializable id);

     /**
     * 获取分页对象
     *
     * @param page
     * @param queryParam
     * @return
     */
     IPage<DictDataQueryVo> getDictDataPageList(@Param("page") Page page, @Param("param") PageModel queryParam);
}

