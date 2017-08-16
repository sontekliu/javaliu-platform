package com.javaliu.platform.modules.auth.service.impl;

import com.javaliu.platform.modules.auth.dao.UserDao;
import com.javaliu.platform.modules.auth.entity.User;
import com.javaliu.platform.modules.auth.exception.UserException;
import com.javaliu.platform.modules.auth.service.IUserService;
import com.javaliu.platform.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService{

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public boolean userExist(String email) throws UserException{
        if(StringUtils.isBlank(email)){
            throw new NullPointerException("email 不能为空");
        }
        boolean b = false;
        int count = userDao.findUserCount(email);
        if(0 != count){
            b = true;
        }
        return b;
    }

    @Override
    public User findUserByCode(String code) {
        return userDao.findUserByCode(code);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        if(null == user){
            throw new UserException("用户信息不能为空");
        }
        userDao.saveOne(user);
    }
}
