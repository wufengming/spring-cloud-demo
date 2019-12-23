package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicesystem.entity.DictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.dict.DictQueryVo;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
* <p>
* 字典类型 Mapper 接口
* </p>
*
* @author wfm
* @since 2019-12-09
*/
public interface DictMapper extends BaseMapper<DictEntity> {

     /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
     DictQueryVo getDictById(Serializable id);

     /**
     * 获取分页对象
     *
     * @param page
     * @param queryParam
     * @return
     */
     IPage<DictQueryVo> getDictPageList(@Param("page") Page page, @Param("param") PageModel queryParam);
}

