/**
 * <br> 项目名：javaliu-platform
 * <br> 文件名：PlatformException.java
 * <br> Copyright 2017 Javaliu
 */
package com.javaliu.platform.exception;

/**
 * <br> 类 名：PlatformException
 * <br> 描 述：平台异常类
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月01日
 * <br> 版 本：V1.0
 */
public class PlatformException extends RuntimeException{
    public PlatformException() {
        super();
    }

    public PlatformException(String message) {
        super(message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }

    protected PlatformException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
