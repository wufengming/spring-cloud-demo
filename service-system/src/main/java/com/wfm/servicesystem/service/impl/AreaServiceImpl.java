package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.entity.AreaEntity;
import com.wfm.servicesystem.mapper.AreaMapper;
import com.wfm.servicesystem.service.AreaService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import com.wfm.servicesystem.model.vo.area.AreaQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 行政区域表 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@Service
public class AreaServiceImpl extends BaseServiceImpl<AreaMapper, AreaEntity> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveArea(AreaEntity entity) throws Exception {
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
    public boolean updateArea(AreaEntity entity) throws Exception {
        // 校验修改的条件 .....

        boolean updateResult = updateById(entity);
        if (!updateResult) {
            throw new DaoException("修改失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteArea(Long id) throws Exception {

        // 删除条件判断 .....

        boolean deleteResult = removeById(id);
        if (!deleteResult) {
            throw new DaoException("删除失败");
        }

        return true;
    }

    @Override
    public AreaQueryVo getAreaById(Serializable id) throws Exception {
        return areaMapper.getAreaById(id);
    }

    @Override
    public Paging<AreaQueryVo> getAreaPageList(PageModel queryParam) throws Exception {
        Page page = setPageParam(queryParam, OrderItem.desc("update_dt"));
        IPage<AreaQueryVo> iPage = areaMapper.getAreaPageList(page, queryParam);
        return new Paging(iPage);
    }

}

