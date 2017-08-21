package com.javaliu.platform.modules.auth.web;

import com.javaliu.platform.Global;
import com.javaliu.platform.modules.auth.entity.User;
import com.javaliu.platform.modules.auth.service.IUserService;
import com.javaliu.platform.security.Digests;
import com.javaliu.platform.utils.IdGenUtils;
import com.javaliu.platform.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping(value = "${adminPath}/user/")
public class UserController extends BaseController{

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * 添加用户信息
     *
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = new User();
        long id = IdGenUtils.genSequenceId();
        String salt = Digests.generateSalt();
        user.setId(id);
        user.setCode("sontek");
        user.setEmail("sontek@gmail.com");
        user.setName("sontek");
        user.setYear(1988);
        user.setMonth(06);
        user.setDay(01);
        user.setSex(0);
        user.setHeaderPic("");
        user.setPassword(Digests.password("admin", salt));
        user.setSalt(salt);
        user.setStatus("0");
        user.setCreateBy(id);
        user.setCreateTime(new Date());
        user.setUpdateBy(id);
        user.setUpdateTime(new Date());
        user.setDeleteFlag(Global.DELETE_FALSE);
        user.setRemark("测试");

        userService.addUser(user);
        return "success";
    }

    @RequestMapping(value = "test")
    public String test(){

        return "success";
    }
}