package com.javaliu.platform.exception.wrapper;

/**
 * 业务异常
 */
public class BusinessWrapperException extends RuntimeException{
    public BusinessWrapperException() {
        super();
    }

    public BusinessWrapperException(String message) {
        super(message);
    }

    public BusinessWrapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessWrapperException(Throwable cause) {
        super(cause);
    }

    protected BusinessWrapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
