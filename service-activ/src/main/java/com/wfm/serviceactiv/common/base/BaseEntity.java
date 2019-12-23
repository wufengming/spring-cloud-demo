package com.wfm.serviceactiv.common.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * description: BaseEntity
 * date: 2019-10-24 14:14
 * author: wfm
 * version: 1.0
 */
@Data
public class BaseEntity<T> implements Serializable {
//
//    /**
//     * 主键
//     */
//    private long id;
//
//    /**
//     * 创建者
//     */
//    private long creator;
//
//    /**
//     * 新增时间
//     */
//    private Date insertDt;
//
//    /**
//     * 更新者
//     */
//    private long editor;
//
//    /**
//     * 更新时间
//     */
//    private Date updateDt;
//
//    /**
//     * 当前组织
//     */
//    private long curOrgid;
//
//    /**
//     *  版本号
//     */
//    private  int recordVer;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 新增时间
     */
    @ApiModelProperty(value = "新增时间")
    @TableField(fill = FieldFill.INSERT)
    private Date insertDt;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long editor;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDt;

    /**
     * 当前组织
     */
    @ApiModelProperty(value = "当前组织")
    @TableField(fill = FieldFill.INSERT)
    private Long curOrgid;

    /**
     *  版本号
     */
    @ApiModelProperty(value = "版本号")
    @Version
    private  Integer recordVer;

}
