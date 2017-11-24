package com.javaliu.platform.exception.handler.impl;

import com.javaliu.platform.exception.handler.ExceptionHandler;

/**
 * <br> 类名：IgnoringHandler
 * <br> 描述：忽略异常处理
 * <br> 作者：javaliu
 * <br> 创建：2017年11月24日
 * <br> 版本：V1.0.0
 */
public class IgnoringHandler implements ExceptionHandler{

    @Override
    public void handle(Exception e, String message) {
        //do nothing, just ignore the exception
    }
}
