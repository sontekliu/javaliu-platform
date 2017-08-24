package com.javaliu.platform.threadlocal;


import com.javaliu.platform.Global;
import com.javaliu.platform.web.PageParam;

public class PageThreadLocal {

    private static final ThreadLocal<PageParam> USER_ENTITY_THREAD_LOCAL = new ThreadLocal<PageParam>();

    public static void set(PageParam pageParam){
        USER_ENTITY_THREAD_LOCAL.set(pageParam);
    }

    public static PageParam get(){
        PageParam pageParam = USER_ENTITY_THREAD_LOCAL.get();
        if(null == pageParam){
            return new PageParam(1, Integer.parseInt(Global.getConfig("custom.page.page_size")));
        }
        return pageParam;
    }

    public static void remove(){
        USER_ENTITY_THREAD_LOCAL.remove();
    }
}
