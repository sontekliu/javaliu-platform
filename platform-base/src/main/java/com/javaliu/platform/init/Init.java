package com.javaliu.platform.init;

import com.javaliu.platform.Global;
import com.javaliu.platform.utils.PropertiesUtils;

public class Init {

    static {
        intiCustomConstant();
    }
    /**
     * 初始化自定义常量
     */
    public static void intiCustomConstant(){
        Global global = Global.getInstance();
        String pageSize = PropertiesUtils.INSTANCE.getPropertyOrDefault("", Global.DEFAULT_PAGE_SIZE + "");
        Global.DEFAULT_PAGE_SIZE = Integer.parseInt(pageSize);
    }
}
