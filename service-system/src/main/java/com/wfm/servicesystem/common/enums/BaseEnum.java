package com.wfm.servicesystem.common.enums;

/**
 * description: 枚举类型父接口
 * date: 2019-11-06 16:48
 * author: wfm
 * version: 1.0
 */
public interface BaseEnum {
    /**
     * 获取枚举索引
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取描述
     *
     * @return
     */
    String getDesc();
}
