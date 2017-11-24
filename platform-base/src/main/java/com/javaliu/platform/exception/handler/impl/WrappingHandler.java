package com.javaliu.platform.exception.handler.impl;

import com.javaliu.platform.exception.handler.ExceptionHandler;

public class WrappingHandler implements ExceptionHandler{

    @Override
    public void handle(Exception e, String message) {
        throw new RuntimeException(message, e);
    }
}
