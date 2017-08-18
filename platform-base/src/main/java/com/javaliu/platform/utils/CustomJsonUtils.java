package com.javaliu.platform.utils;

import com.alibaba.fastjson.serializer.*;

import java.util.HashMap;
import java.util.Map;

public class CustomJsonUtils {

    /**
     * 将对象转化成字符串
     * @param object
     * @return
     */
    public static String toJSONString(Object object){
        Map<Class, ObjectSerializer> map = new HashMap<Class, ObjectSerializer>();
        map.put(Long.class, ToStringSerializer.instance);
        // map.put(Long.TYPE, ToStringSerializer.instance);
        return toJSONString(object, map, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 将对象转化成字符串
     * @param object    待转化的对象
     * @param clazz     数据类型转化器，如 Long.class ToStringSerializer.instance 表示将 Long 转化成字符串，注意一定要是包装类
     * @param features  序列化特色
     * @return
     */
    public static String toJSONString(Object object, Map<Class, ObjectSerializer> clazz,
                                      SerializerFeature... features){

        SerializeWriter out = new SerializeWriter(features);
        try {
            //此处必须new一个SerializeConfig,防止修改默认的配置
            JSONSerializer serializer = new JSONSerializer(out, new SerializeConfig());
            for (Map.Entry<Class, ObjectSerializer> entry : clazz.entrySet()){
                serializer.getMapping().put(entry.getKey(), entry.getValue());
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }
}
