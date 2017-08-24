package com.javaliu.platform.utils;

import com.javaliu.platform.modules.auth.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * <br> 类 名：UserUtils
 * <br> 描 述：用户工具类
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月17日
 * <br> 版 本：V1.0
 */
public class UserUtils {

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if(null == user){
            user = new User();
        }
        return user;
    }
}
