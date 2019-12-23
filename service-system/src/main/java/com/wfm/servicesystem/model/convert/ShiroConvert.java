package com.wfm.servicesystem.model.convert;

import com.wfm.servicesystem.config.jwt.JwtToken;
import com.wfm.servicesystem.model.vo.shiro.JwtTokenRedisVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: ShiroConvert
 * date: 2019-12-23 10:52
 *
 * @author: wfm
 * @version: 1.0
 */
@Mapper
public interface ShiroConvert {

    ShiroConvert INSTANCE = Mappers.getMapper(ShiroConvert.class);

    /**
     * JwtToken对象转换成JwtTokenRedisVo
     *
     * @param jwtToken
     * @return
     */
    JwtTokenRedisVo jwtTokenToJwtTokenRedisVo(JwtToken jwtToken);
}
