package com.javaliu.platform.common.annotation;

import com.javaliu.platform.common.constant.LogFromEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {

    /**
     * 用户动作
     * @return
     */
    public String action() default "";

    /**
     * 动作来自哪儿
     * @return
     */
    public LogFromEnum from() default LogFromEnum.WEB;
}
