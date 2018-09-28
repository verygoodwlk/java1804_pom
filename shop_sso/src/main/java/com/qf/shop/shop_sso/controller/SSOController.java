package com.qf.shop.shop_sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.qf.entity.ResultData;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.util.Constact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private IUserService userService;
    
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    /**
     * 统一进行登录
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpServletResponse response){

        ResultData<User> data = userService.login(username, password);
        System.out.println("-->" + data);
        switch (data.getCode()){
            case 0:
                //登录成功的处理

                //1、生成uuid
                String token = UUID.randomUUID().toString();
                //2、调用redis将token作为key，user作为value放入redis中
                redisTemplate.opsForValue().set(token, data.getData());
                redisTemplate.expire(token, 7, TimeUnit.DAYS);
                //3、将cookie写入浏览器中
                Cookie cookie = new Cookie(Constact.LOGIN_TOKEN, token);
                cookie.setMaxAge(7 * 24 * 60 * 60);//设置cookie的超时时间
                cookie.setPath("/");
//                cookie.setDomain(".shop.com");
                response.addCookie(cookie);

                return "redirect:http://localhost:8081";
            default:
                //登录失败 - 回到登录页面
                model.addAttribute("data", data);
                return "login";
        }
    }

    /**
     * 认证校验
     * @return
     */
    @RequestMapping("/islogin")
    @ResponseBody
    public String isLogin(@CookieValue(value = "login_token", required = false) String login_token){
        System.out.println("-->"+ login_token);
        //1、cookie - login_token - uuid
        //2、uuid - redis - user
        User user = null;
        if(login_token != null){
            user = (User) redisTemplate.opsForValue().get(login_token);
        }
        System.out.println("-->    123" + user);
        return "callback(" + new Gson().toJson(user) + ")";
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(@CookieValue(value = "login_token", required = false) String login_token,
                         HttpServletResponse response){

        //删除redis
        redisTemplate.delete(login_token);
        //删除cookie
        Cookie cookie = new Cookie(Constact.LOGIN_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "login";
    }
}
