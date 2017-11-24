package com.javaliu.platform.exception.wrapper;


/**
 * DAO异常包装类
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
