package com.zte.springboot_08_ssmp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivilegeInterceptor implements HandlerInterceptor {

    //目标方法执行之前 执行
//    @Override
////    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//////        return HandlerInterceptor.super.preHandle(request, response, handler);
////    }
//    //目标方法执行之后，视图对象返回之前执行
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//    //流程都执行完毕后 执行
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
////        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}
