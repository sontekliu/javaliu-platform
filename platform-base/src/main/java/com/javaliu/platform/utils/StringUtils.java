package com.javaliu.platform.utils;

/**
 * 字符串工具类
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils{

    /**
     * 如果对象时null，则返回空字符串
     * @param obj      对象本身
     * @return
     * @author  javaliu
     */
    public static String blankReturnEmpty(Object obj){
        return null == obj ? "" : String.valueOf(obj);
    }

    /**
     * 首字母大写
     * @param str   字符串
     * @return
     * @author  javaliu
     * @date    2016.08.25
     */
    public static String initCap(String str){
        if(isBlank(str) || isEmpty(str)){
            return null;
        }
        char[] chars = str.toCharArray();
        if(chars[0] >= 'a' && chars[0] <= 'z'){
            chars[0] = (char)(chars[0] - 32);
        }
        return new String(chars);
    }
}