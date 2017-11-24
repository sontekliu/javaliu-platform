package com.javaliu.platform.exception.handler.impl;

import com.javaliu.platform.exception.handler.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <br> 类名：CollectingHandler
 * <br> 描述：聚集所有发生的异常
 * <br> 作者：javaliu
 * <br> 创建：2017年11月24日
 * <br> 版本：V1.0.0
 */
public class CollectingHandler implements ExceptionHandler{

    private List exceptions = new ArrayList();

    public List getExceptions(){
        return this.exceptions;
    }

    @Override
    public void handle(Exception e, String message){
        this.exceptions.add(e);

        //message is ignored here, but could have been
        //collected too.
    }
}
