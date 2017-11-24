package com.javaliu.platform.exception.wrapper;

/**
 * 服务层包装类
 */
public class ServiceWrapperException extends RuntimeException{
    public ServiceWrapperException() {
        super();
    }

    public ServiceWrapperException(String message) {
        super(message);
    }

    public ServiceWrapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceWrapperException(Throwable cause) {
        super(cause);
    }

    protected ServiceWrapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
