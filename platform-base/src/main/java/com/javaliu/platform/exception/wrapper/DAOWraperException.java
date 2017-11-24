package com.javaliu.platform.exception.wrapper;


/**
 * <br> 类名：DAOWraperException
 * <br> 描述：DAO层异常处理类
 * <br> 作者：javaliu
 * <br> 创建：2017年11月24日
 * <br> 版本：V1.0.0
 */
public class DAOWraperException extends RuntimeException{
    public DAOWraperException() {
        super();
    }

    public DAOWraperException(String message) {
        super(message);
    }

    public DAOWraperException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOWraperException(Throwable cause) {
        super(cause);
    }

    protected DAOWraperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
