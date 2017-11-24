package com.javaliu.platform.modules.auth.service.impl;

import com.javaliu.platform.exception.wrapper.BusinessWrapperException;
import com.javaliu.platform.modules.auth.dao.LogDao;
import com.javaliu.platform.modules.auth.entity.Log;
import com.javaliu.platform.modules.auth.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService implements ILogService {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogDao logDao;

    @Transactional
    @Override
    public Log saveLog(Log log){
        if(null == log){
            throw new BusinessWrapperException("日志对象不能为空");
        }
        Log resultLog = logDao.saveOne(log);
        return resultLog;
    }

}
