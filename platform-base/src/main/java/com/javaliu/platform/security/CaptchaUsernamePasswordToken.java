package com.javaliu.platform.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * <br> 类 名：CaptchaUsernamePasswordToken
 * <br> 描 述：加入验证码的token
 * <br> 作 者：liushijun
 * <br> 创 建：2017年08月17日
 * <br> 版 本：V1.0
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    //验证码字符串
    private String captcha;

    public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username,password,rememberMe, host);
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String captcha) {
        super(username,password,rememberMe);
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken(String username, char[] password, String captcha) {
        super(username,password);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
