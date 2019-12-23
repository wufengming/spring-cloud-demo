package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.MenuButtonEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.menubutton.MenuButtonQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 菜单按钮 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
public interface MenuButtonService extends BaseService<MenuButtonEntity> {

    /**
    * 保存
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean saveMenuButton(MenuButtonEntity entity) throws Exception;

    /**
    * 修改
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean updateMenuButton(MenuButtonEntity entity) throws Exception;

    /**
    * 删除
    *
    * @param id
    * @return
    * @throws Exception
    */
    boolean deleteMenuButton(Long id) throws Exception;

    /**
    * 根据ID获取查询对象
    *
    * @param id
    * @return
    * @throws Exception
    */
    MenuButtonQueryVo getMenuButtonById(Serializable id) throws Exception;

    /**
    * 获取分页对象
    *
    * @param queryParam
    * @return
    * @throws Exception
    */
    Paging<MenuButtonQueryVo> getMenuButtonPageList(PageModel queryParam) throws Exception;

}

