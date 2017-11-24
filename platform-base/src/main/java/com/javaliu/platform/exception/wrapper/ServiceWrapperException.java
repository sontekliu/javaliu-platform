package com.javaliu.platform.exception.wrapper;

/**
 * <br> 类名：ServiceWrapperException
 * <br> 描述：Service层异常处理类
 * <br> 作者：javaliu
 * <br> 创建：2017年11月24日
 * <br> 版本：V1.0.0
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
