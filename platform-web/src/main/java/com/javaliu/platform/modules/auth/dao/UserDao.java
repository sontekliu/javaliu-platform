package com.javaliu.platform.modules.auth.dao;

import com.javaliu.platform.Global;
import com.javaliu.platform.common.BaseDao;
import com.javaliu.platform.modules.auth.entity.User;
import com.javaliu.platform.modules.auth.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class UserDao extends BaseDao<User>{

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    /**
     * 根据 Email 查询用户的数量
     * @param email
     * @return
     */
    public int findUserCount(String email){
        int count = 0;
        String sql = "SELECT count(*) AS count FROM sys_user WHERE email = ? AND delete_flag = ?";
        Object[] params = new Object[]{email, Global.DELETE_FALSE};
        try {
            Object object = this.findby(sql, "count", params);
            count = Integer.parseInt(object.toString());
        } catch (SQLException e) {
            logger.error("根据 email {} 查询用户数量异常", email, e);
            throw new UserException("根据 email "+ email +" 查询用户数量异常", e);
        }
        return count;
    }
}
