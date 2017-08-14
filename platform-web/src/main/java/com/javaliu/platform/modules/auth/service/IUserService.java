package com.javaliu.platform.modules.auth.service;

public interface IUserService {

    /**
     * 根据 email 查询用户是否存在
     * @param email
     * @return
     */
    public boolean userExist(String email);
}
