package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.entity.DictEntity;
import com.wfm.servicesystem.mapper.DictMapper;
import com.wfm.servicesystem.service.DictService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import com.wfm.servicesystem.model.vo.dict.DictQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 字典类型 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, DictEntity> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveDict(DictEntity entity) throws Exception {
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
    public boolean updateDict(DictEntity entity) throws Exception {
        // 校验修改的条件 .....

        boolean updateResult = updateById(entity);
        if (!updateResult) {
            throw new DaoException("修改失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDict(Long id) throws Exception {

        // 删除条件判断 .....

        boolean deleteResult = removeById(id);
        if (!deleteResult) {
            throw new DaoException("删除失败");
        }

        return true;
    }

    @Override
    public DictQueryVo getDictById(Serializable id) throws Exception {
        return dictMapper.getDictById(id);
    }

    @Override
    public Paging<DictQueryVo> getDictPageList(PageModel queryParam) throws Exception {
        Page page = setPageParam(queryParam, OrderItem.desc("update_dt"));
        IPage<DictQueryVo> iPage = dictMapper.getDictPageList(page, queryParam);
        return new Paging(iPage);
    }

}

