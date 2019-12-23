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
 * 组织账本的关系
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_org_account")
@ApiModel(value="OrgAccountEntity对象", description="组织账本的关系")
public class OrgAccountEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "组织主键")
    private Long orgid;

    @ApiModelProperty(value = "组织编码")
    private String orgcode;

    @ApiModelProperty(value = "账本编码")
    private String accountCode;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;


}
