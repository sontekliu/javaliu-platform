package com.javaliu.platform.exception.wrapper;

/**
 * <br> 类名：BusinessWrapperException
 * <br> 描述：业务处理异常
 * <br> 作者：javaliu
 * <br> 创建：2017年11月24日
 * <br> 版本：V1.0.0
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
