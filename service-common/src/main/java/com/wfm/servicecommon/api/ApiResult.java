package com.wfm.servicecommon.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description: ApiResult
 * date: 2019-10-24 18:49
 * author: wfm
 * version: 1.0
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {
    /**
     *返回状态码,返回200的成功，其他的都是失败的
     */
    private int code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    /**
     * 状态
     */
    private boolean state;

    public ApiResult() { }

    public static ApiResult result(boolean flag){
        if (flag){
            return ok();
        }
        return fail("");
    }

    public static ApiResult result(ApiCode apiCode){
        return result(apiCode,null);
    }

    public static ApiResult result(ApiCode apiCode,Object data){
        return result(apiCode,null,data);
    }

    public static ApiResult result(ApiCode apiCode,String msg,Object data){
        //ApiCode 的状态码的信息
        String message = apiCode.getMsg();
        boolean codeState=false;
        if (StringUtils.isNotBlank(msg)){
            message = msg;
        }
        if(apiCode.getCode()==200){
            codeState=true;
        }


        return ApiResult.builder()
                .code(apiCode.getCode())
                .msg(message)
                .data(data)
                .time(new Date())
                .state(codeState)
                .build();
    }

    public static ApiResult ok(){
        return ok(null);
    }

    public static ApiResult ok(Object data){
        return result(ApiCode.SUCCESS,data);
    }

    public static ApiResult ok(Object data,String msg){
        return result(ApiCode.SUCCESS,msg,data);
    }

    public static ApiResult okMap(String key,Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        return ok(map);
    }

    public static ApiResult fail(ApiCode apiCode){
        return result(apiCode,null);
    }

    public static ApiResult fail(String msg){
        return result(ApiCode.FAIL,msg,null);

    }

    public static ApiResult fail(ApiCode apiCode,Object data){
        if (ApiCode.SUCCESS == apiCode){
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode,data);

    }

    public static ApiResult fail(String key,Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        return result(ApiCode.FAIL,map);
    }

    public static ApiResult fail() {
        return fail(ApiCode.FAIL);
    }
}
