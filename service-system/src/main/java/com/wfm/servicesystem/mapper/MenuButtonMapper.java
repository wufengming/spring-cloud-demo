package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicesystem.entity.MenuButtonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.menubutton.MenuButtonQueryVo;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
* <p>
* 菜单按钮 Mapper 接口
* </p>
*
* @author wfm
* @since 2019-12-09
*/
public interface MenuButtonMapper extends BaseMapper<MenuButtonEntity> {

     /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
     MenuButtonQueryVo getMenuButtonById(Serializable id);

     /**
     * 获取分页对象
     *
     * @param page
     * @param queryParam
     * @return
     */
     IPage<MenuButtonQueryVo> getMenuButtonPageList(@Param("page") Page page, @Param("param") PageModel queryParam);
}

