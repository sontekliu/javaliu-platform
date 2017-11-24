package com.javaliu.platform.exception.handler.impl;

import com.javaliu.platform.exception.handler.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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
