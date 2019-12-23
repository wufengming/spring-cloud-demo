package com.wfm.servicecommon.exception;

/**
 * description: ShiroConfigException
 * date: 2019-12-18 17:58
 *
 * @author: wfm
 * @version: 1.0
 */
public class ShiroConfigException extends SpringBootException {

    public ShiroConfigException(String message) {
        super(message);
    }

    public ShiroConfigException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
