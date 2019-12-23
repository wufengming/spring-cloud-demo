package com.wfm.servicesystem.common.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * description: BaseEntity
 * date: 2019-10-24 14:14
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
public class BaseEntity<T> implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
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

    public Integer getRecordVer() {
        if(recordVer==null){
            return 1;//去除该属性的前后空格并进行非空非null判断
        }
        return recordVer;
    }

    public void setRecordVer(Integer recordVer) {
        this.recordVer = recordVer == null ? 1 : recordVer;
    }
}
