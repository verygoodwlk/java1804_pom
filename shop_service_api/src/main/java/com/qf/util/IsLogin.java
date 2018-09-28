package com.qf.util;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IsLogin {

    boolean tologin() default false;
}
