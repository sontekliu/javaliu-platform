package com.javaliu.platform.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <br> 类 名：IncorrectCaptchaException
 * <br> 描 述：验证码验证失败异常
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月17日
 * <br> 版 本：V1.0
 */
public class IncorrectCaptchaException extends AuthenticationException {
    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String detail) {
        super(detail);
    }

    public IncorrectCaptchaException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
