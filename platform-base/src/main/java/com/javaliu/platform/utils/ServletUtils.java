package com.javaliu.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet 工具类
 */
public class ServletUtils {

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(ServletUtils.class);

    /**
     * 获取请求IP地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String userAddress = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(userAddress)) {
            userAddress = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(userAddress)) {
            userAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(userAddress)) {
            userAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(userAddress)) {
            userAddress = request.getRemoteAddr();
        }
        LOG.info("IP --> {}", userAddress);
        return userAddress;
    }
}
