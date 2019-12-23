package com.wfm.servicesystem.model.convert;


import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.entity.UserOrgEntity;
import com.wfm.servicesystem.model.vo.login.LoginUserVo;
import com.wfm.servicesystem.model.vo.shiro.LoginUserRedisVo;
import com.wfm.servicesystem.model.vo.user.UserOrgRoleVo;
import com.wfm.servicesystem.model.vo.user.UserQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * description: 系统用户转换器
 * date: 2019-11-11 15:27
 * author: wfm
 * version: 1.0
 *
 * @Mapper 定义这是一个MapStruct对象属性转换接口，在这个类里面规定转换规则
 * 在项目构建时，会自动生成改接口的实现类，这个实现类将实现对象属性值复制
 */
@Mapper
public interface UserConvert {
    /**
     * 获取该类自动生成的实现类的实例
     * 接口中的属性都是 public static final 的
     * 方法都是public abstract 的
     */
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    /**
     * Vo转User
     *
     * @p userQueryVo  这个参数就是源对象，也就是需要被复制的对象
     * @return  返回的是目标对象，就是最终的结果对象
     *
     *     这个方法就是用于实现对象属性复制的方法
     *     @Mapping 用来定义属性复制规则  source 指定源对象属性  target 指定目标对象属性
     */
    UserEntity VoToUser(UserQueryVo userQueryVo);

    /**
     * 获取用户组织角色的关系
     * @param list
     * @return
     */
    List<UserOrgEntity> VoToUserOrg(List<UserOrgRoleVo> list);

    /**
     * LoginUserVo对象转换成LoginUserRedisVo
     *
     * @param loginUserVo
     * @return
     */
    LoginUserRedisVo loginUserVoToLoginUserRedisVo(LoginUserVo loginUserVo);
}
