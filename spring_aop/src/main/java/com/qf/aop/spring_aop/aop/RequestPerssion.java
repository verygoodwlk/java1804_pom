package com.qf.aop.spring_aop.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestPerssion {

    //权限的名称
    String value();
}
