package com.javaliu.platform.modules.auth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

}
