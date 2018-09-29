package com.qf.util;

import com.qf.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
public class LoginAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 环绕增强
     */
    @Around("execution(* *..*Controller.*(..)) && @annotation(com.qf.util.IsLogin)")
    public Object isLogin(ProceedingJoinPoint proceedingJoinPoint){

        //获取目标方法上的自定义注解IsLogin
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        IsLogin islogin = method.getAnnotation(IsLogin.class);


        String token = null;

        //判断是否登录
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(Constact.LOGIN_TOKEN)){
                    token = cookie.getValue();
                    break;
                }
            }
        }

        User user = null;

        if(token != null){
            //查询redis
            user = (User) redisTemplate.opsForValue().get(token);
        }


        //判断是否需要跳转到登录页页面 - 有些方法可能需要强制登录
        if(user == null && islogin.tologin()){
            String returnUrl = request.getRequestURL() + "?" + request.getQueryString();
            returnUrl = returnUrl.replace("&", "*");
            return "redirect:http://localhost:8084/sso/tologin?returnUrl=" + returnUrl;
        }


        Object[] args = proceedingJoinPoint.getArgs();
        //已经登录
        //获得目标方法的实参列表
        for(int i = 0; i < args.length; i++){
            if(args[i] != null && args[i].getClass()==User.class){
                args[i] = user;
            }
        }


        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed(args);//放行目标方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
