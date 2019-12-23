package com.wfm.servicesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wfm.servicecommon.exception.BusinessException;
import com.wfm.servicecommon.exception.DaoException;
import com.wfm.servicecommon.vo.Paging;
import com.wfm.servicesystem.model.base.PageModel;
import com.wfm.servicesystem.entity.DictDataEntity;
import com.wfm.servicesystem.mapper.DictDataMapper;
import com.wfm.servicesystem.service.DictDataService;
import com.wfm.servicesystem.common.base.BaseServiceImpl;
import com.wfm.servicesystem.model.vo.dictdata.DictDataQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 字典数据 服务实现类
 * </p>
 *
 * @author wfm
 * @since 2019-12-09
 */
@Slf4j
@Service
public class DictDataServiceImpl extends BaseServiceImpl<DictDataMapper, DictDataEntity> implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveDictData(DictDataEntity entity) throws Exception {
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
    public boolean updateDictData(DictDataEntity entity) throws Exception {
        // 校验修改的条件 .....

        boolean updateResult = updateById(entity);
        if (!updateResult) {
            throw new DaoException("修改失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDictData(Long id) throws Exception {

        // 删除条件判断 .....

        boolean deleteResult = removeById(id);
        if (!deleteResult) {
            throw new DaoException("删除失败");
        }

        return true;
    }

    @Override
    public DictDataQueryVo getDictDataById(Serializable id) throws Exception {
        return dictDataMapper.getDictDataById(id);
    }

    @Override
    public Paging<DictDataQueryVo> getDictDataPageList(PageModel queryParam) throws Exception {
        Page page = setPageParam(queryParam, OrderItem.desc("update_dt"));
        IPage<DictDataQueryVo> iPage = dictDataMapper.getDictDataPageList(page, queryParam);
        return new Paging(iPage);
    }

}

