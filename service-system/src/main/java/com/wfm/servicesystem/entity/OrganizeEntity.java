package com.wfm.servicesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.wfm.servicesystem.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织表
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_organize")
@ApiModel(value="OrganizeEntity对象", description="组织表")
public class OrganizeEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父级")
    private Long parentId;

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
    private Date serviceStartdate;

    @ApiModelProperty(value = "服务期限结束时间")
    private Date serviceEnddate;

    @ApiModelProperty(value = "授权加密信息")
    private String empowerInfo;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "审核标识")
    private Integer verifyState;

    @ApiModelProperty(value = "审核时间")
    private Date verifyDate;

    @ApiModelProperty(value = "审核意见")
    private String verifyOpinion;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;



}
