package com.zte.springboot_08_ssmp.proxy.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect
public class MyAspect {

    @Before(value = "execution(* com.zte.springboot_08_ssmp.service.impl.OneServiceImpl.getAll())")
    public void before(){
        System.out.println("代理对象前");
    }

    public void afterRunning(){

    }
    //    环绕代理
//    @Around(value = "execution(* com.zte.springboot_08_ssmp.mapper.*Mapper.*())")
//    public Object around(MethodInvocationProceedingJoinPoint mipjp) throws Throwable{
//
//        @Before()
//        Object proceed = mipjp.proceed();
//        @After()
//        return proceed;
//    }

    public void afterThrowing(){}

    public void after(){}
}
