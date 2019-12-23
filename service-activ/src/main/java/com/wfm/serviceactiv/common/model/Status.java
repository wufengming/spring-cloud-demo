package com.wfm.serviceactiv.common.model;

/**
 * description: Status
 * date: 2019-10-29 13:51
 * author: wfm
 * version: 1.0
 */
public class Status {
    //状态码
    public final static String SUCCESS = "000";
    public final static String FAIL = "001";
    public final static String NO_LOGIN = "003";
    public final static String NO_PRIVILEGE = "004";

    //资源状态
    public final static int DELETE = 0;
    public final static int ENABLE = 1;
    public final static int DISABLE = 2;
}
