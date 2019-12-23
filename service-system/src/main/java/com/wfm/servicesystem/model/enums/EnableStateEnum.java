package com.wfm.servicesystem.model.enums;

import com.wfm.servicesystem.common.enums.BaseEnum;

/**
 * description: EnableStateEnum
 * date: 2019-11-06 16:46
 * author: wfm
 * version: 1.0
 */
public enum EnableStateEnum implements BaseEnum {
    DISABLE(0,"禁用"),
    ENABLE(1,"启用");

    private Integer code;
    private String desc;

    EnableStateEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
