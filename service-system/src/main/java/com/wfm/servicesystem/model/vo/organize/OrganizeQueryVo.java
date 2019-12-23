package com.wfm.servicesystem.model.vo.organize;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * description: 组织 查询结果对象
 * date: 2019-11-08 17:06
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "OrganizeQueryVo对象",description = "组织查询参数")
public class OrganizeQueryVo implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父级")
    private Long parentid;

    @ApiModelProperty(value = "父编码")
    private String parentCode;

    @ApiModelProperty(value = "分类")
    private String types;

    @ApiModelProperty(value = "编码")
    private String encode;

    @ApiModelProperty(value = "检索编码（用-区分层级信息）")
    private String orderCode;

    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "行政区划码")
    private String areaCode;

    @ApiModelProperty(value = "行政区划名称")
    private String areaName;

    @ApiModelProperty(value = "统一社会信用代码")
    private String enterpriseCode;

    @ApiModelProperty(value = "统一社会信用附件地址")
    private String enterpriseAttachment;

    @ApiModelProperty(value = "层次")
    private Integer levels;

    @ApiModelProperty(value = "单位性质")
    private String unitNature;

    @ApiModelProperty(value = "行业属性")
    private String tradeType;

    @ApiModelProperty(value = "单位负责人")
    private String manager;

    @ApiModelProperty(value = "联系电话")
    private String mobilephone;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "单位地址")
    private String address;

    @ApiModelProperty(value = "核编人数")
    private Integer formationNum;

    @ApiModelProperty(value = "在职人数")
    private Integer jobNum;

    @ApiModelProperty(value = "离退休人数")
    private Integer retireNum;

    @ApiModelProperty(value = "临时人数")
    private Integer temproaryNum;

    @ApiModelProperty(value = "服务期限开始时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceStartdate;

    @ApiModelProperty(value = "服务期限结束时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceEnddate;

    @ApiModelProperty(value = "授权加密信息")
    private String empowerInfo;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "审核标识")
    private Integer verifyState;

    @ApiModelProperty(value = "审核时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date verifyDate;

    @ApiModelProperty(value = "审核意见")
    private String verifyOpinion;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "版本号")
    private  Integer recordVer;


}
