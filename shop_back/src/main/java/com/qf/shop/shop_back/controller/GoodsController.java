package com.qf.shop.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @RequestMapping("/goodslist")
    public String goodsManager(){

        //通过service获得商品列表
        List<Goods> goods = goodsService.queryAll();
        System.out.println("查询的所有的商品：" + goods);

        return "goodslist";
    }
}
