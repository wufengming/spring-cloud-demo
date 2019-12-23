package com.wfm.servicesystem.model.vo.shiro;

import com.wfm.servicecommon.vo.ClientInfo;
import com.wfm.servicesystem.model.vo.login.LoginUserVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录用户Redis对象，后台使用
 * description: LoginUserRedisVo
 * date: 2019-12-23 11:30
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class LoginUserRedisVo extends LoginUserVo {
    private static final long serialVersionUID = -3858850188055605806L;

    /**
     * 包装后的盐值
     */
    private String salt;

    /**
     * 用户客户端信息对象
     */
    private ClientInfo clientInfo;
}
