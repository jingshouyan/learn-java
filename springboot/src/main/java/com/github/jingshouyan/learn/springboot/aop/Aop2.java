package com.github.jingshouyan.learn.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * aop
 *
 * @author jingshouyan
 * 2021-07-05 16:48
 **/
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class Aop2 {

    @Around("@within(ctl)")
    public Object around(ProceedingJoinPoint joinPoint, RestController ctl) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println(method.toGenericString());
        System.out.println("aop2");
        return "aop2";
//        return joinPoint.proceed();
    }
}
