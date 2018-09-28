package com.qf.aop.spring_aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */
@Aspect
@Component
@Order(200)
public class HelloAop {

    /**
     * aop的增强方法 - 切点
     * 切点表达式
     */
    @Around("execution(* com.qf.aop.spring_aop.HelloController.*(..))")
    public Object test1(ProceedingJoinPoint proceedingJoinPoint){
        Object object = null;
        System.out.println("前置的准备....");

        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("资源的回收....");
        return object;
    }
}
