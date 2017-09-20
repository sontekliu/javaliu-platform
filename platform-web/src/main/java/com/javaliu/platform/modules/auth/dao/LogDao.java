package com.javaliu.platform.modules.auth.dao;

import com.javaliu.platform.common.BaseDao;
import com.javaliu.platform.exception.DaoException;
import com.javaliu.platform.modules.auth.entity.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class LogDao extends BaseDao<Log>{

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    /**
     * 添加日志信息
     * @param  log
     * @return
     */
    public Log saveOne(Log log){
        StringBuffer str = new StringBuffer();
        str.append("INSERT INTO sys_log (id, action, action_from, ip_address, user_id, user_code,");
        str.append("delete_flag, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        Object[] params = new Object[]{log.getId(), log.getAction(), log.getActionFrom(), log.getIpAddress(),
                 log.getUserId(), log.getUserCode(), log.getDeleteFlag(), log.getCreateTime()};
        Log logBean = null;
        try {
            logBean = this.insert(str.toString(), Log.class, params);
        } catch (SQLException e) {
            logger.error("添加日志记录失败", e);
            throw new DaoException("添加日志记录失败", e);
        }
        return logBean;
    }
}
