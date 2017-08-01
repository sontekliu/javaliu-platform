package com.javaliu.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.function.Function;

/**
 *  Properties文件读取工具类
 */
public enum PropertiesUtils {

    /**
     * 枚举方式创建单例模式
     */
    INSTANCE;

    /**
     * 读取到的配置信息（内存）
     */
    private static Properties properties;

    /**
     * 初始化读取配置
     */
    static {
        properties = PropertiesReader.readAllProperties();
    }

    /**
     * 根据key获取配置值
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * 根据key获取配置值，如果配置值为null，则返回默认值
     * @param key
     * @param defaultVal 默认值
     * @return
     */
    public String getPropertyOrDefault(String key, String defaultVal) {
        return properties.get(key) != null ? properties.getProperty(key): defaultVal;
    }

    /**
     * 是否包含该key的配置
     * @param key
     * @return
     */
    public boolean containsKey(String key){
        return properties.containsKey(key);
    }

    /**
     * 读取.properties文件的内部类
     */
    private static class PropertiesReader{
        /**
         * 日志记录器
         */
        private static final Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);

        /**
         * 配置文件后缀名常量
         */
        private static final String PROPERTIES_SUFFIX = ".properties";

        /**
         * 递归各个文件夹读取配置文件，如果是文件执行回调函数
         * @param root 根目录
         * @param isFileThenDoFunction 如果是文件，则执行该回调函数
         * @return
         */
        private static Properties recursiveFile(File root, Function<File,Properties> isFileThenDoFunction){
            Properties properties = new Properties();
            if(null != root && root.exists()) {
                File[] files = root.listFiles();
                //并发流读取properties文件
                Arrays.asList(files).parallelStream().forEach(file -> {
                    if(file.isFile()){
                        properties.putAll(isFileThenDoFunction.apply(file));
                    }else{
                        properties.putAll(recursiveFile(file, isFileThenDoFunction));
                    }
                });
            }
            return properties;
        }

        /**
         * 判断是否配置文件，是则读取出来并返回
         * @param file 需读取的文件
         * @return
         */
        private static Properties checkAndReadProperties(File file){
            boolean b = file.getName().contains(".");
            if(!b){
                return new Properties();
            }
            String suffix = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
            if(PROPERTIES_SUFFIX.equals(suffix)){
                Properties properties = new Properties();
                try {
                    properties.load(new BufferedInputStream(new FileInputStream(file)));
                    return properties;
                } catch (IOException e) {
                    LOG.error("加载配置文件失败", e);
                }
            }
            return new Properties();
        }


        /**
         * 读取所有目录配置文件
         * @return
         */
        public static Properties readAllProperties(){
            //获取class根目录
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            File root = new File(path);
            return readProperties(root);
        }

        /**
         * 读取指定目录配置文件
         * @param path 指定目录
         * @return
         */
        private static Properties readProperties(File path){
            return PropertiesReader.recursiveFile(path, PropertiesReader::checkAndReadProperties);
        }
    }
}
