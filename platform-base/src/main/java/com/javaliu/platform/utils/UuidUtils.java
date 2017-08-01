package com.javaliu.platform.utils;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UuidUtils {

    /**
     * 生成UUID的方法，此UUID不带"-"
     * @return
     * @author javaliu
     */
    public static String generateUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
