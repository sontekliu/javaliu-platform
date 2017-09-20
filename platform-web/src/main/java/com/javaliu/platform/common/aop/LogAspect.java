/**
 * <br> 项目名：elecontract
 * <br> 文件名：LogAspect.java
 * <br> Copyright 2017 国信嘉宁数据技术有限公司
 */
package com.javaliu.platform.common.aop;

import com.javaliu.platform.common.BaseEntity;
import com.javaliu.platform.common.annotation.ActionLog;
import com.javaliu.platform.common.constant.LogFromEnum;
import com.javaliu.platform.modules.auth.entity.Log;
import com.javaliu.platform.modules.auth.entity.User;
import com.javaliu.platform.modules.auth.service.ILogService;
import com.javaliu.platform.threadlocal.IPThreadLocal;
import com.javaliu.platform.utils.IdGenUtils;
import com.javaliu.platform.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * <br> 类 名：LogAspect
 * <br> 描 述：日志记录的切面类
 * <br> 作 者：liushijun
 * <br> 创 建：2017年08月28日
 * <br> 版 本：V1.0
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(com.javaliu.platform.common.annotation.ActionLog)")
    private void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = getHttpServletRequest();
        String uri = request.getRequestURI();
        LOG.info("===============记录操作日志开始===================  {}", uri);
        option(joinPoint, "0");
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint){
        HttpServletRequest request = getHttpServletRequest();
        String uri = request.getRequestURI();
        LOG.info("===============记录操作日志结束===================  {}", uri);
        //option(joinPoint, "1");
    }

    /**
     * 获取请求的操作名称
     * @param joinPoint
     * @return
     */
    private void option(JoinPoint joinPoint, String flag){
        User currentUser = UserUtils.getCurrentUser();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ActionLog actionLog = method.getAnnotation(ActionLog.class);
        String action = actionLog.action();
        LogFromEnum from = actionLog.from();
        Log sysLog = new Log();
        sysLog.setId(IdGenUtils.genSequenceId());
        sysLog.setAction(action);
        sysLog.setActionFrom(from.getValue());
        sysLog.setUserId(currentUser.getId());
        sysLog.setUserCode(currentUser.getCode());
        sysLog.setIpAddress(IPThreadLocal.get());
        sysLog.setDeleteFlag(BaseEntity.DELETE_FALSE);
        sysLog.setCreateTime(new Date());
        try {
            logService.saveLog(sysLog);
        }catch (Exception e){
            LOG.error("日志记录失败", e);
        }
    }

    /**
     * 获取HttpServletRequest对象
     * @return
     */
    private HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
