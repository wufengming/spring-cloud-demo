package com.wfm.servicesystem.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description: 自定义公共字段填充处理器
 * date: 2019-11-06 14:23
 * author: wfm
 * version: 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取到需要被填充的字段的值

        System.out.println("*******插入操作 满足填充条件*********");
        //获取当前登录用户,组织信息


        //创建者
        setFieldValByName("creator", 0L, metaObject);
        //创建时间
        setFieldValByName("insertDt", new Date(), metaObject);
        //当前组织ID
        setFieldValByName("curOrgid", 0L, metaObject);
        //创建时间
        setFieldValByName("editor", 0L, metaObject);
        //更新时间
        setFieldValByName("updateDt", new Date(), metaObject);

    }

    /**
     * 修改操作自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        System.out.println("*******修改操作 满足填充条件*********");
        //获取当前登录用户,组织信息


        //创建时间
        setFieldValByName("editor", 0L, metaObject);
        //更新时间
        setFieldValByName("updateDt", new Date(), metaObject);

    }
}
