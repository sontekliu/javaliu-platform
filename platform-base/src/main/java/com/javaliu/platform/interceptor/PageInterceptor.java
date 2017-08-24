package com.javaliu.platform.interceptor;

import com.javaliu.platform.Global;
import com.javaliu.platform.threadlocal.PageThreadLocal;
import com.javaliu.platform.web.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * <br> 类 名：PageInterceptor
 * <br> 描 述：分页拦截器
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月21日
 * <br> 版 本：V1.0
 */
public class PageInterceptor implements HandlerInterceptor {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(PageInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {

        String _pageNow = request.getParameter("pageNow");
        String _pageSize = request.getParameter("pageSize");
        int pageNow = 1;
        int pageSize = Integer.parseInt(Global.getConfig("custom.page.page_size"));
        if(StringUtils.isNotBlank(_pageNow)){
            pageNow = Integer.parseInt(_pageNow);
        }
        if(StringUtils.isNotBlank(_pageSize)){
            pageSize = Integer.parseInt(_pageSize);
        }
        PageParam pageParam = new PageParam(pageNow, pageSize);
        PageThreadLocal.set(pageParam);
        setParamNameAndValue(request);
        return true;
    }

    /**
     * 取得页面传递的参数，并将其以相同参数名的方式返还页面
     * @param request
     */
    private void setParamNameAndValue(HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
        while(enumeration.hasMoreElements()){
            String paramName = enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            if(logger.isDebugEnabled()) {
                logger.debug("参数名称：{}, 参数值： {} ", paramName, paramValue);
            }
            request.setAttribute(paramName, paramValue);
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        PageThreadLocal.remove();
    }
}
