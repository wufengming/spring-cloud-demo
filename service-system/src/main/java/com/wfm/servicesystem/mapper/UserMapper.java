package com.wfm.servicesystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfm.servicesystem.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfm.servicesystem.model.vo.user.UserQueryParam;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wfm
 * @since 2019-11-06
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 根据ID获取查询对象
     *
     * @param userid
     * @param orgid
     * @return
     */
    UserQueryVo getSysUserById(@Param("userid") Long userid,@Param("orgid") Long orgid);

    /**
     * 获取分页对象
     *
     * @param page
     * @param param
     * @return
     */
    IPage<UserQueryVo> getSysUserPageList(@Param("page") Page page, @Param("param") UserQueryParam param);
}
