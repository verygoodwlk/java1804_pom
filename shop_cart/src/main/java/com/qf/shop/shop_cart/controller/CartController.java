package com.qf.shop.shop_cart.controller;

import com.qf.entity.Cart;
import com.qf.entity.User;
import com.qf.util.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    /**
     * 添加购物车
     * @return
     */
    @IsLogin
    @RequestMapping("/addcart")
    public String addCart(Cart cart, User user){
        System.out.println("添加购物车：" + cart);
        System.out.println("是否登录：" + user);
        //判断当前是否登录
        if(user != null){
            //添加到数据库
        } else {
            //添加到cookie中
        }

        return "";
    }
}
