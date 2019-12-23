package com.wfm.servicesystem.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfm.servicecommon.utils.CommonUtil;
import com.wfm.servicesystem.model.base.PageModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * description: BaseServiceImpl
 * date: 2019-10-24 18:15
 * author: wfm
 * version: 1.0
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    protected Page setPageParam(PageModel queryParam) {
        return setPageParam(queryParam,null);
    }

    protected Page setPageParam(PageModel queryParam, OrderItem defaultOrder) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(queryParam.getPageIndex());
        // 设置页大小
        page.setSize(queryParam.getPageSize());
        /**
         * 如果是PageModel 含有排序信息，并且不为空，则使用前端排序
         * 否则使用默认排序 List<OrderItem>
         */
        List<OrderItem> orderItems = queryParam.getOrders();
        if (CommonUtil.isEmpty(orderItems)) {
            //判断不为空
            if(!CommonUtil.isEmpty(defaultOrder)) {
                page.setOrders(Arrays.asList(defaultOrder));
            }
        } else {

            //合并defaultOrder 和orderItems的值
            if(!CommonUtil.isEmpty(defaultOrder)) {
                orderItems.add(defaultOrder);
            }

            page.setOrders(orderItems);
        }

        return page;
    }
}
