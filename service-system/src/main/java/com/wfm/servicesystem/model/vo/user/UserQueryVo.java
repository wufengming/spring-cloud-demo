package com.wfm.servicesystem.model.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * description: UserQueryVo
 * date: 2019-10-31 10:10
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserQueryVo对象", description = "系统用户查询参数")
public class UserQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String realname;

    @ApiModelProperty(value = "用户类型:普通操作员、系统管理员、业务管理员")
    private Integer types;

    @ApiModelProperty(value = "是否管理员")
    private Integer isAdmin;

    @ApiModelProperty(value = "登录方式:密码、ca、ca+密码")
    private Integer loginType;

    @ApiModelProperty(value = "手机")
    private String mobilephone;

    @ApiModelProperty(value = "固定电话")
    private String telephone;

    @ApiModelProperty(value = "头像地址")
    private String headicon;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @ApiModelProperty(value = "身份证号")
    private String cardNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "默认组织主键")
    private Integer orgid;

    @ApiModelProperty(value = "默认部门主键")
    private Integer departmentPhid;

    @ApiModelProperty(value = "审核标识")
    private Integer verifyState;

    @ApiModelProperty(value = "审核时间")
    private Date verifyDate;

    @ApiModelProperty(value = "审核意见")
    private String verifyOpinion;

    @ApiModelProperty(value = "第一次访问时间")
    private Date firstVisitDate;

    @ApiModelProperty(value = "最后访问时间")
    private Date lastVisitDate;

    @ApiModelProperty(value = "最后修改密码日期")
    private Date cPwdDate;

    @ApiModelProperty(value = "允许同时有多用户登录")
    private Integer multiUserlogin;

    @ApiModelProperty(value = "登录次数")
    private Integer logonCount;

    @ApiModelProperty(value = "在线状态")
    private Integer userOnline;

    @ApiModelProperty(value = "排序码")
    private Integer sortOrder;

    @ApiModelProperty(value = "有效标志")
    private Integer enabledmark;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "版本号")
    private  Integer recordVer;

    @ApiModelProperty(value = "角色关系信息")
    private List<UserOrgRoleVo> userorgrolelist;


}
