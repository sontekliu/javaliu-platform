package com.javaliu.platform.exception.handler;

/**
 * <br> 类名：ExceptionHandler
 * <br> 描述：异常处理接口
 * <br> 作者：javaliu
 * <br> 创建：2017年11月24日
 * <br> 版本：V1.0.0
 */
public interface ExceptionHandler {

    /**
     * 异常处理接口
     * @param e
     * @param message
     */
    public void handle(Exception e, String message);
}
