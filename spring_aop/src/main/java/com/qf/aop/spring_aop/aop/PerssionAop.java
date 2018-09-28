package com.qf.aop.spring_aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(100)
public class PerssionAop {

//    @Autowired
//    private MyRealm myRealm;

    @Around("@annotation(com.qf.aop.spring_aop.aop.RequestPerssion)")
    public Object test2(ProceedingJoinPoint proceedingJoinPoint){

        //获得进入目标方法需要的权限
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestPerssion annotation = method.getAnnotation(RequestPerssion.class);

        //目标方法需要的权限名称
        String mustPerssion = annotation.value();

        //当前用户拥有的权限值
        String havePerssion = "update";

        if(havePerssion.equals(mustPerssion)){
            try {
               return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        throw new RuntimeException("骚瑞，你没有权限");
    }
}
