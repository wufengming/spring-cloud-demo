package com.wfm.servicecommon.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * @author Exrickx
 */
public class CommonUtil {

    /**
     * 以UUID重命名
     *
     * @param fileName
     * @return
     */
    public static String renamePic(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString().replace("-", "") + extName;
    }

    /**
     * 随机6位数生成
     */
    public static String getRandomNum() {

        Random random = new Random();
        int num = random.nextInt(999999);
        //不足六位前面补0
        String str = String.format("%06d", num);
        return str;
    }

    /**
     * 批量递归删除时 判断target是否在ids中 避免重复删除
     * @param target
     * @param ids
     * @return
     */
    public static Boolean judgeIds(String target, String[] ids){

        Boolean flag = false;
        for(String id : ids){
            if(id.equals(target)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断对象是否为空
     * @param obj 对象
     * @return
     */
    public static Boolean isEmpty(Object obj){
        Boolean isEmpty=false;
        if(obj==null){
            isEmpty=true;
        }else {
            if(obj instanceof String){
                isEmpty= StringUtils.isBlank((String)obj);
            }else if(obj instanceof Collection){
                isEmpty= CollectionUtils.isEmpty((Collection<?>) obj);
            }else if(obj instanceof HashMap) {
                isEmpty = MapUtils.isEmpty((HashMap) obj);
            }
        }

        return isEmpty;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获取本机IP
     */
    public static String getIp() {
        String ip = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
