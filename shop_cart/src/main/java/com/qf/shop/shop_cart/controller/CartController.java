package com.qf.shop.shop_cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.entity.Cart;
import com.qf.entity.User;
import com.qf.service.ICartService;
import com.qf.service.IGoodsService;
import com.qf.util.Constact;
import com.qf.util.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference
    private ICartService cartService;

    @Reference
    private IGoodsService goodsService;

    /**
     * 添加购物车
     * @return
     */
    @IsLogin
    @RequestMapping("/addcart")
    public String addCart(Cart cart,
                          User user,
                          HttpServletResponse response,
                          @CookieValue(value="cart_token", required = false) String carts){

//        System.out.println("添加购物车：" + cart);
//        System.out.println("是否登录：" + user);
        //判断当前是否登录
        if(user != null){
            //添加到数据库
            cart.setUid(user.getId());
            cartService.addCart(cart);
        } else {
            //添加到cookie中

            List<Cart> cartList = null;
            if(carts != null){
                //cookie中已经有购物车信息
                TypeToken<List<Cart>> tt = new TypeToken<List<Cart>>(){};
                cartList = new Gson().fromJson(carts, tt.getType());
                cartList.add(cart);
            } else {
                //cookie中没有购物车信息
                //cookie - key/value必须是string  cookie中不能有中文  cookie最多只能放4K的数据
                cartList = Collections.singletonList(cart);
            }

            System.out.println("添加购物车的信息：" + cartList);


            String json = new Gson().toJson(cartList);
            try {
                json = URLEncoder.encode(json, "utf-8"); // [{:}] %5B%7B%22gid%22%3A74%2C%22gnumber%22%3A3%7D%5D
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie(Constact.CART_TOKEN, json);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");

            response.addCookie(cookie);
        }

        return "addsucc";
    }

    /**
     * 购物车合并
     * @return
             */
    @RequestMapping("/hebing")
    @ResponseBody
    public String heBing(Integer uid, @CookieValue(value="cart_token", required = false) String carts){
        //carts -> List<Cart>
        TypeToken<List<Cart>> tt = new TypeToken<List<Cart>>(){};
        List<Cart> cartList = new Gson().fromJson(carts, tt.getType());

        for(Cart cart : cartList){
            cart.setUid(uid);
            cartService.addCart(cart);
        }
        return "succ";
    }

    /**
     * ajax获取购物车列表
     * @return
     */
    @IsLogin
    @RequestMapping("/getcarts")
    @ResponseBody
    public String getCarts(@CookieValue(value = "cart_token", required = false) String carts, User user){
        List<Cart> cartsList = cartService.getCarts(user, carts);
        return "getCart(" + new Gson().toJson(cartsList) + ")";
    }

    /**
     * 去购物车列表
     * @return
     */
    @IsLogin
    @RequestMapping("/cartlist")
    public String cartList(@CookieValue(value = "cart_token", required = false) String carts,
                           User user,
                           Model model){
        List<Cart> cartsList = cartService.getCarts(user, carts);
        model.addAttribute("carts", cartsList);
        return "cartlist";
    }
}
