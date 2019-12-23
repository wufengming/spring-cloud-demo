package com.wfm.servicesystem.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: DateUtil
 * date: 2019-11-29 17:03
 * author: wfm
 * version: 1.0
 */
public class DateUtil {

    public static final String formatStr_yyyyMMddHHmmssS_ = "yyyyMMddHHmmss";
    public static final String formatStr_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String formatStr_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String formatStr_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String formatStr_yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String formatStr_yyyyMMdd = "yyyy-MM-dd";
    public static final String formatStr_yyyy = "yyyy";
    public static final String formatStr_yyyy_MM_dd = "yyyyMMdd";
    public static final String formatStr_yyyyMMddDelimiter = "-";

    public static String getYYYYMMDDHHMMSS(Date date){
        if (date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }
}
