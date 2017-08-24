package com.javaliu.platform.security;

import com.google.code.kaptcha.Constants;
import com.javaliu.platform.exception.IncorrectCaptchaException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <br> 类 名：KaptchaFilter
 * <br> 描 述：验证码过滤器
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月17日
 * <br> 版 本：V1.0
 */
public class KaptchaFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(KaptchaFilter.class);

    public static final String DEFAULT_CAPTCHA_PARAM = "kaptcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        CaptchaUsernamePasswordToken token = createToken(request, response);
        try {
            doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch(AuthenticationException e) {
            return onLoginFailure(token, e ,request,response);
        }
    }

    /**
     * 重写该方法，让其跳转到登录成功页面，否则默认shiro去session中找出之前的保存的请求，如果没有则跳转到successUrl
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {

        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request,response, getSuccessUrl());
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }

    /**
     * 校验验证码
     * @param request
     * @param token
     * @throws IncorrectCaptchaException
     */
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token)
            throws IncorrectCaptchaException {
        // 从session中获取验证码
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 校验
        if (null == captcha || !captcha.equals(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误");
        }
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String host = getHost(request);
        boolean rememberMe = isRememberMe(request);
        String captcha = getCaptcha(request);

        return new CaptchaUsernamePasswordToken(username,password.toCharArray(),rememberMe,host,captcha);
    }

    protected  String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    //保存异常对象到request
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
}
