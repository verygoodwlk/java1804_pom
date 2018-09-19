package com.qf.shop.shop_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 默认访问index命名的页面
     * 如果没有可以通过controller设置欢迎页
     * @return
     */
    @RequestMapping("/")
    public String welcome(){
        return "index";
    }

    /**
     * 统一的页面跳转处理
     * @param pagename
     * @return
     */
    @RequestMapping("/topage/{pagename}")
    public String toPage(@PathVariable("pagename") String pagename){
        return pagename;
    }
}
