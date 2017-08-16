package com.javaliu.platform.modules.auth.service;

import com.javaliu.platform.modules.auth.entity.User;

public interface IUserService {

    /**
     * 根据 email 查询用户是否存在
     * @param email
     * @return
     */
    public boolean userExist(String email);

    /**
     * 根据用户名称查找用户
     * @param code
     * @return
     */
    public User findUserByCode(String code);

    /**
     * 添加用户信息
     * @param user
     */
    public void addUser(User user);
}
