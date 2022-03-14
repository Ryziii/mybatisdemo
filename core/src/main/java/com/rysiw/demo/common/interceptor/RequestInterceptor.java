package com.rysiw.demo.common.interceptor;

import com.rysiw.demo.annotations.VerifyToken;
import com.rysiw.demo.common.constant.SecurityConstants;
import com.rysiw.demo.common.utils.JwtTokenUtils;
import com.rysiw.demo.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Rysiw
 * @date 2021/8/1 00:54
 */
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        VerifyToken verifyToken = method.getAnnotation(VerifyToken.class);
        if(verifyToken == null)
            return true;

        logger.info("Interceptor preHandle is running.");
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        String username = request.getHeader("username");
        return tokenService.doIsTokenValid(username, token);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("Interceptor postHandle is running.");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("Interceptor afterHandle is running.");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}
