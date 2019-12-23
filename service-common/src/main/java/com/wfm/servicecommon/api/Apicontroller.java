package com.wfm.servicecommon.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: Apicontroller
 * date: 2019-10-24 18:44
 * author: wfm
 * version: 1.0
 */
@Slf4j
@RestController
public class Apicontroller {
    /**
     * <p>
     * 请求成功
     * </p>
     *
     * @param data 数据内容
     * @param <T>  对象泛型
     * @return
     */
    protected <T> ApiResult<T> ok(T data) {
        return ApiResult.ok(data);
    }

    /**
     * <p>
     * 请求失败
     * </p>
     *
     * @param msg 提示内容
     * @return
     */
    protected ApiResult<Object> fail(String msg) {
        return ApiResult.fail(msg);
    }

    /**
     * <p>
     * 请求失败
     * </p>
     *
     * @param apiCode 请求错误码
     * @return
     */
    protected ApiResult<Object> fail(ApiCode apiCode) {
        return ApiResult.fail(apiCode);
    }
}
