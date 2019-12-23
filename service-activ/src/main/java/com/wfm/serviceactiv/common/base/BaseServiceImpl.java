package com.wfm.serviceactiv.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * description: BaseServiceImpl
 * date: 2019-10-24 18:15
 * author: wfm
 * version: 1.0
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
