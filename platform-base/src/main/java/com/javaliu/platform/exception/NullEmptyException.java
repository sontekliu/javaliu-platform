/**
 * <br> 项目名：elecontract
 * <br> 文件名：NullEmptyException.java
 * <br> Copyright 2017 国信嘉宁数据技术有限公司
 */
package com.javaliu.platform.exception;

/**
 * <br> 类 名：NullEmptyException
 * <br> 描 述：空指针异常
 * <br> 作 者：liushijun
 * <br> 创 建：2017年08月29日
 * <br> 版 本：V1.0
 */
public class NullEmptyException extends RuntimeException{

    public NullEmptyException() {
        super();
    }

    public NullEmptyException(String message) {
        super(message);
    }

    public NullEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullEmptyException(Throwable cause) {
        super(cause);
    }

    protected NullEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
