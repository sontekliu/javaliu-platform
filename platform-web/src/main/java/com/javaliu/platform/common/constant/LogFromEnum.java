/**
 * <br> 项目名：elecontract
 * <br> 文件名：LogEnum.java
 * <br> Copyright 2017 国信嘉宁数据技术有限公司
 */
package com.javaliu.platform.common.constant;

/**
 * <br> 类 名：LogFromEnum
 * <br> 描 述：日志来自哪儿枚举
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月28日
 * <br> 版 本：V1.0
 */
public enum LogFromEnum {

    WEB("WEB", "WEB请求");

    private String value;
    private String description;

    private LogFromEnum(String value, String description){
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
