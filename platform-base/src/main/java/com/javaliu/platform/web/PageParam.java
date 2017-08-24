package com.javaliu.platform.web;

/**
 * <br> 类 名：PageParam
 * <br> 描 述：分页参数
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月21日
 * <br> 版 本：V1.0
 */
public class PageParam {

    private int pageNow;
    private int pageSize;

    public PageParam(int pageNow, int pageSize){
        this.pageNow = pageNow;
        this.pageSize = pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
