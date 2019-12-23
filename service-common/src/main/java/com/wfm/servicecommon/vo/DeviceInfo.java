package com.wfm.servicecommon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * description: DeviceInfo
 * date: 2019-12-23 11:09
 *
 * @author: wfm
 * @version: 1.0
 */
@Data
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = -5912785220335057555L;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String model;
}
