package com.wfm.servicesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * description: 系统日志
 * date: 2019-12-18 13:13
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

//    /**
//     * id
//     */
//    private String id;
//
//    /**
//     * 创建人
//     */
//    private String createBy;
//
//    /**
//     * 创建时间
//     */
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createTime;
//
//    /**
//     * 更新人
//     */
//    private String updateBy;
//
//    /**
//     * 更新时间
//     */
//    private Date updateTime;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 请求类名、方法名
     */
    private String method;

    /**
     * 操作人用户名称
     */
    private String username;
    /**
     * 操作人用户账户
     */
    private String userid;
    /**
     * 操作详细日志
     */
    private String logContent;

    /**
     * 日志类型（1:登录日志; 2:操作日志; 3:定时任务;）
     */
    private Integer logType;
}
