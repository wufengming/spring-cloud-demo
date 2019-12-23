

package com.wfm.servicecommon.exception;



/**
 * 验证码校验异常
 *
 * @author wfm
 * @date 2018-11-08
 */
public class VerificationCodeException extends SpringBootException {

    public VerificationCodeException(String message) {
        super(message);
    }

    public VerificationCodeException(Integer errorCode, String message) {
        super(errorCode, message);
    }

}
