package com.javaliu.platform.common;

import java.util.Date;

/**
 * 实体基类
 */
public class BaseEntity {

    // 表示未删除
    public static final String DELETE_FALSE = "0";
    // 表示已删除
    public static final String DELETE_TRUE = "1";

    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private String deleteFlag;
    private String remark;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
