package com.wfm.servicesystem.model.convert;

import com.wfm.servicesystem.entity.MenuEntity;
import com.wfm.servicesystem.model.vo.menu.MenuQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: MenuConvert
 * date: 2019-12-04 09:14
 * author: wfm
 * version: 1.0
 */
@Mapper
public interface MenuConvert {
    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    /**
     * Vo转MenuEntity
     *
     * @param menuQueryVo
     * @return
     */
    MenuEntity VoToMenu(MenuQueryVo menuQueryVo);

}
