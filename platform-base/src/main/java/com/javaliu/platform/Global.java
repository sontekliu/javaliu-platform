/**
 * <br> 项目名：javaliu-platform
 * <br> 文件名：Global.java
 * <br> Copyright 2017 Javaliu
 */
package com.javaliu.platform;

import com.google.common.collect.Maps;
import com.javaliu.platform.utils.PropertiesUtils;
import com.javaliu.platform.utils.StringUtils;

import java.util.Map;

/**
 * <br> 类 名：Global
 * <br> 描 述：全局变量
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月01日
 * <br> 版 本：V1.0
 */
public class Global {

    /**
     * 当前对象实例
     */
    private static Global global = new Global();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 本系统默认编码
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     *  是／否
     */
    public static final int YES = 1;
    public static final int NO = 0;

    /**
     *  显示／隐藏
     */
    public static final String SHOW = "1";
    public static final String HIDE = "0";

    /**
     * 已删除／未删除
     */
    public static final String DELETE_TRUE = "1";
    public static final String DELETE_FALSE = "0";

    /**
     * 每页显示条数
     */
    public static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 线程池中线程的最大值
     */
    public static int DEFAULT_THREAD_MAX_SIZE = 30;
    /**
     * 线程池的大小
     */
    public static int DEFAULT_THREAD_CORE_SIZE = 30;
    /**
     * 线程池中阻塞队列的大小
     */
    public static int DEFAULT_THREAD_QUEUE_SIZE = 100;
    /**
     * 空闲线程在线程池中所空闲的时间
     */
    public static int DEFAULT_THREAD_KEEP_TIME = 100;

    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

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

    /**
     * 获取配置
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = PropertiesUtils.INSTANCE.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
