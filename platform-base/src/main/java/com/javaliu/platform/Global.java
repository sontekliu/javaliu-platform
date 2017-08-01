/**
 * <br> 项目名：javaliu-platform
 * <br> 文件名：Global.java
 * <br> Copyright 2017 Javaliu
 */
package com.javaliu.platform;

/**
 * <br> 类 名：Global
 * <br> 描 述：全局变量
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月01日
 * <br> 版 本：V1.0
 */
public class Global {

    /**
     * 本系统默认编码
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 获取常量值 ${fns:getConst()}
     * @param field
     * @return
     */
    public static Object getConst(String field){
        Object object = null;
        try {
            object = Global.class.getField(field).get(null);
        } catch (Exception e) {
            //发生异常代表无此属性
        }
        return object;
    }
}
