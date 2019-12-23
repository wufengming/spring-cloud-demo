package com.wfm.servicecommon.aspect;

import com.wfm.servicecommon.constant.CommonConstant;

import java.lang.annotation.*;

/**
 * description: 定义系统日志注解
 * date: 2019-12-18 11:33
 *
 * @author: wfm
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {
    /**
     * 日志内容
     *
     * @return
     */
    String value() default "";

    /**
     * 日志类型
     *
     * @return 1:登录日志; 2:操作日志; 3:定时任务;
     */
    int logType() default CommonConstant.LOG_TYPE_2;
}
