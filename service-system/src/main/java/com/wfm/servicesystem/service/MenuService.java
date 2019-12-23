package com.wfm.servicesystem.service;

import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.entity.MenuEntity;
import com.wfm.servicesystem.common.base.BaseService;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.model.vo.menu.MenuQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 菜单模块 服务类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
public interface MenuService extends BaseService<MenuEntity> {

    /**
    * 保存
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean saveMenu(MenuEntity entity) throws Exception;

    /**
    * 修改
    *
    * @param entity
    * @return
    * @throws Exception
    */
    boolean updateMenu(MenuEntity entity) throws Exception;

    /**
    * 删除
    *
    * @param id
    * @return
    * @throws Exception
    */
    boolean deleteMenu(Long id) throws Exception;

    /**
    * 根据ID获取查询对象
    *
    * @param id
    * @return
    * @throws Exception
    */
    MenuQueryVo getMenuById(Serializable id) throws Exception;

    /**
    * 获取分页对象
    *
    * @param queryParam
    * @return
    * @throws Exception
    */
    Paging<MenuQueryVo> getMenuPageList(PageModel queryParam) throws Exception;

}

