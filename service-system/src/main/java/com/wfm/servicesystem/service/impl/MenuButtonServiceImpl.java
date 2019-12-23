package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.entity.MenuButtonEntity;
import com.wfm.servicesystem.mapper.MenuButtonMapper;
import com.wfm.servicesystem.service.MenuButtonService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import com.wfm.servicesystem.model.vo.menubutton.MenuButtonQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 菜单按钮 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@Service
public class MenuButtonServiceImpl extends BaseServiceImpl<MenuButtonMapper, MenuButtonEntity> implements MenuButtonService {

    @Autowired
    private MenuButtonMapper menuButtonMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveMenuButton(MenuButtonEntity entity) throws Exception {
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
    public boolean updateMenuButton(MenuButtonEntity entity) throws Exception {
        // 校验修改的条件 .....

        boolean updateResult = updateById(entity);
        if (!updateResult) {
            throw new DaoException("修改失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMenuButton(Long id) throws Exception {

        // 删除条件判断 .....

        boolean deleteResult = removeById(id);
        if (!deleteResult) {
            throw new DaoException("删除失败");
        }

        return true;
    }

    @Override
    public MenuButtonQueryVo getMenuButtonById(Serializable id) throws Exception {
        return menuButtonMapper.getMenuButtonById(id);
    }

    @Override
    public Paging<MenuButtonQueryVo> getMenuButtonPageList(PageModel queryParam) throws Exception {
        Page page = setPageParam(queryParam, OrderItem.desc("update_dt"));
        IPage<MenuButtonQueryVo> iPage = menuButtonMapper.getMenuButtonPageList(page, queryParam);
        return new Paging(iPage);
    }

}

