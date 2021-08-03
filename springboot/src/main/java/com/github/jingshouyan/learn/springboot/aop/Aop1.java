package com.github.jingshouyan.learn.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * aop
 *
 * @author jingshouyan
 * 2021-07-05 16:48
 **/
@Component
@Aspect
public class Aop1 {

    @Around("@within(ctl)")
    public Object around(ProceedingJoinPoint joinPoint, RestController ctl) throws Throwable {
        System.out.println("aop1");
        return joinPoint.proceed();
    }
}
