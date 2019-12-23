
package com.wfm.servicecommon.exception;

/**
 * 业务异常
 * @author wfm
 * @date 2018-11-08
 */
public class BusinessException extends SpringBootException {

    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(Integer errorCode, String message) {
        super(errorCode,message);
    }

}
