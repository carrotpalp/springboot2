package com.springboot2.carrot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class UserAop {
    @Around("execution(* com.hexin.fund.service.impl..*(..)) ")
    public Object simpleAop(final ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        System.out.println("AOP:"+Arrays.asList(args));

        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("return:"+proceed);
        return proceed;
    }
}
