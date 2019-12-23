package com.wfm.servicesystem.service.impl;

import com.wfm.servicecommon.exception.SpringBootException;
import com.wfm.servicecommon.utils.JacksonUtil;
import com.wfm.servicesystem.entity.SysLog;
import com.wfm.servicesystem.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description: SysLogServicelmpl
 * date: 2019-12-18 14:42
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
@Service
public class SysLogServicelmpl implements SysLogService {

    /**
     * 日志暂时打印在控制台，日志的存储现在未做
     * @param sysLog
     * @return
     */
    @Override
    public boolean save(SysLog sysLog) {
        try {
            // 这里就不做具体实现了
            log.info(JacksonUtil.BeanToJson(sysLog));
            return true;
        }catch (Exception e){
            throw new SpringBootException(e.getMessage());
        }

    }

}
