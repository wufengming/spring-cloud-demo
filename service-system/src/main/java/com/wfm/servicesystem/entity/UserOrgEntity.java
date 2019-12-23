package com.wfm.servicesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.wfm.servicesystem.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户组织角色关系表
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_user_org")
@ApiModel(value="UserOrgEntity对象", description="用户组织角色关系表")
public class UserOrgEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    @ApiModelProperty(value = "组织主键")
    private Long orgid;

    @ApiModelProperty(value = "组织编码")
    private String orgcode;

    @ApiModelProperty(value = "角色主键")
    private Long rolePhid;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;


}
