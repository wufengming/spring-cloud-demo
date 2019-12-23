package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.entity.MenuEntity;
import com.wfm.servicesystem.mapper.MenuMapper;
import com.wfm.servicesystem.service.MenuService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import com.wfm.servicesystem.model.vo.menu.MenuQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 菜单模块 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveMenu(MenuEntity entity) throws Exception {
        // 校验新增的条件 .....

        entity.setId(null);
        boolean saveResult = super.save(entity);
        if (!saveResult) {
            throw new DaoException("新增失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMenu(MenuEntity entity) throws Exception {
        // 校验修改的条件 .....

        boolean updateResult = updateById(entity);
        if (!updateResult) {
            throw new DaoException("修改失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMenu(Long id) throws Exception {

        // 删除条件判断 .....

        boolean deleteResult = removeById(id);
        if (!deleteResult) {
            throw new DaoException("删除失败");
        }

        return true;
    }

    @Override
    public MenuQueryVo getMenuById(Serializable id) throws Exception {
        return menuMapper.getMenuById(id);
    }

    @Override
    public Paging<MenuQueryVo> getMenuPageList(PageModel queryParam) throws Exception {
        Page page = setPageParam(queryParam, OrderItem.desc("update_dt"));
        IPage<MenuQueryVo> iPage = menuMapper.getMenuPageList(page, queryParam);
        return new Paging(iPage);
    }



}

