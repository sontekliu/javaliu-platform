package com.javaliu.platform.modules.auth.entity;

import com.javaliu.platform.common.BaseEntity;

/**
 * 系统操作日志类
 */
public class Log extends BaseEntity{

    private Long id;
    private String action;
    private String actionFrom;
    private String ipAddress;
    private Long userId;
    private String userCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionFrom() {
        return actionFrom;
    }

    public void setActionFrom(String actionFrom) {
        this.actionFrom = actionFrom;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
