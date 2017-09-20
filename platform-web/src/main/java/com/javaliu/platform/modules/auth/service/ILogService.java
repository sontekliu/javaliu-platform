package com.javaliu.platform.modules.auth.service;

import com.javaliu.platform.modules.auth.entity.Log;

public interface ILogService {

    /**
     * 保存日志
     * @param log
     * @return
     */
    public Log saveLog(Log log);
}
