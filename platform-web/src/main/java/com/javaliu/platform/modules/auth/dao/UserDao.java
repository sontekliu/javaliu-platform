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

    /**
     * 根据用户邮箱查询用户信息
     * @param email
     * @return
     */
    public User findUserByEmail(String email){
        StringBuffer str = new StringBuffer();
        str.append("SELECT id, email, code, name, header_pic AS headerPic, password, salt, status, ")
           .append("sex, year, month, day, delete_flag AS deleteFlag, create_by AS createBy, create_time AS createTime, ")
           .append("update_by AS updateBy, update_time AS updateTime FROM sys_user WHERE email = ? ")
           .append(" AND delete_flag = ?");
        Object[] params = new Object[]{email, Global.DELETE_FALSE};
        User user = null;
        try {
            user = this.findOne(str.toString(), User.class, params);
        } catch (SQLException e) {
            logger.error("根据 email {} 查询用户信息异常", email, e);
            throw new UserException("根据 email "+ email +" 查询用户信息异常", e);
        }
        return user;
    }

    /**
     * 根据登陆用户名称查询用户信息
     * @param code
     * @return
     */
    public User findUserByCode(String code){
        StringBuffer str = new StringBuffer();
        str.append("SELECT id, email, code, name, header_pic AS headerPic, password, salt, status, ")
           .append("sex, year, month, day, delete_flag AS deleteFlag, create_by AS createBy, create_time AS createTime, ")
           .append("update_by AS updateBy, update_time AS updateTime FROM sys_user WHERE code = ? ")
           .append(" AND delete_flag = ?");
        Object[] params = new Object[]{code, Global.DELETE_FALSE};
        User user = null;
        try {
            user = this.findOne(str.toString(), User.class, params);
        } catch (SQLException e) {
            logger.error("根据 code {} 查询用户信息异常", code, e);
            throw new UserException("根据 code "+ code +" 查询用户信息异常", e);
        }
        return user;
    }

    /**
     * 添加用户信息
     * @param  user
     * @return
     */
    public User saveOne(User user){
        StringBuffer str = new StringBuffer();
        str.append("INSERT INTO sys_user (id, email, code, name, header_pic, password, salt, status, ");
        str.append("sex, year, month, day, delete_flag, create_by, create_time, update_by, ");
        str.append("update_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        Object[] params = new Object[]{user.getId(), user.getEmail(), user.getCode(), user.getName(),
        user.getHeaderPic(), user.getPassword(), user.getSalt(), user.getStatus(), user.getSex(),
        user.getYear(), user.getMonth(), user.getDay(), user.getDeleteFlag(), user.getCreateBy(),
        user.getCreateTime(), user.getUpdateBy(), user.getUpdateTime()};
        try {
            user = this.insert(str.toString(), User.class, params);
        } catch (SQLException e) {
            logger.error("添加用户信息异常", e);
            throw new UserException("添加用户信息异常", e);
        }
        return user;
    }
}
