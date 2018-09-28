package com.qf.aop.spring_aop;

import com.qf.aop.spring_aop.aop.RequestPerssion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * spring使用aop
 * 1、spring-aop
 * 2、aop联盟
 * 3、aspectj
 */
@Controller
public class HelloController {

    @RequestPerssion("add")
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("核心业务方法。。。");
        return "Ok！！";
    }


    @RequestPerssion("update")
    @RequestMapping("/hello2")
    @ResponseBody
    public String hello2(){
        System.out.println("核心业务2方法。。。");
        return "Ok！！";
    }
}
