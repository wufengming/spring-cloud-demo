package com.wfm.servicesystem.service;

import com.wfm.servicesystem.entity.SysLog;

/**
 * description: SysLogService
 * date: 2019-12-18 14:42
 *
 * @author: wfm
 * @version: 1.0
 */
public interface SysLogService {
    /**
     * 保存日志
     * @param sysLog
     * @return
     */
    boolean save(SysLog sysLog);
}
