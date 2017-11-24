package com.javaliu.platform.exception.handler.impl;

import com.javaliu.platform.exception.handler.ExceptionHandler;

public class IgnoringHandler implements ExceptionHandler{

    @Override
    public void handle(Exception e, String message) {
        //do nothing, just ignore the exception
    }
}
