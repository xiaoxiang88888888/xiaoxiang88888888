package com.xiaoxiang.base.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * 在模板渲染中加入一些全局变量
 *
 * @author xiang.xiaox
 */

public class VelocityInterceptor extends HandlerInterceptorAdapter {
    private static String WWW_DOMAIN_WEB = "www.domain.web";
    private static String WWW_DOMAIN_STATIC = "www.domain.static";
    private static String SERVER_STATIC_VERSION = "server.static.version";

    @Autowired
    private Properties commonProp;

    //生成视图之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("VelocityInterceptor preHandle");
        modelAndView.addObject("webServer",commonProp.get(WWW_DOMAIN_WEB));
        modelAndView.addObject("staticServer",commonProp.get(WWW_DOMAIN_STATIC));
        modelAndView.addObject("jsServer",commonProp.get(WWW_DOMAIN_STATIC)+"/js/");
        modelAndView.addObject("cssServer",commonProp.get(WWW_DOMAIN_STATIC)+"/css/");
        modelAndView.addObject("imagesServer",commonProp.get(WWW_DOMAIN_STATIC)+"/images/");
        modelAndView.addObject("staticVersion",commonProp.get(SERVER_STATIC_VERSION));
    }
}
