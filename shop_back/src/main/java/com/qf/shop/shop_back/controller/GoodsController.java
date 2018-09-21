package com.qf.shop.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 跨域的问题：
 * 同源策略原则：同协议、同ip（同域名）、同端口
 * 当前端的资源访问后端服务的时候，如果不符合同源策越就发生了所谓的跨域
 *
 * 1、浏览器规定，from表单不用遵循同源策略
 * 2、通常来说，跨域问题是浏览器的行为，不是服务器的行为。一般来说浏览器不会限制跨域的请求发出，而是限制跨域的响应返回
 * 3、浏览器的跨域限制是为了安全考虑
 *
 * 跨域的解决办法：
 * 1、前台先发送ajax到自己的controller中，然后自己的controller再通过某种方式请求到其他系统的接口数据，然后再返回到页面上
 * 2、jquery的jsonp方式实现跨域的解决（利用了浏览器的漏洞）
 * 3、直接采用springmvc的跨域解决办法 - springmvc会在响应头中设置一个参数，浏览器看到这个参数以后就不会拦截跨域请求了
 *  @CrossOrgin
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Reference
    private IGoodsService goodsService;

    @Value("${image.path}")
    private String path;

    @RequestMapping("/goodslist")
    public String goodsManager(Model model){

        //通过service获得商品列表
        List<Goods> goods = goodsService.queryAll();
        System.out.println("查询的所有的商品：" + goods);
        model.addAttribute("goods", goods);
        model.addAttribute("path", path);

        return "goodslist";
    }


    @RequestMapping("/goodsadd")
    public String goodsAdd(@RequestParam("file") MultipartFile file, Goods goods) throws IOException {

        //进行文件的上传 - fastdfs - 获得fastdfs回写的url
        StorePath spath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), "JPG", null);
        String fullPath = spath.getFullPath();

        //将url放入goods对象中
        goods.setGimage(fullPath);

        //调用serivce层保存进数据库
        goods = goodsService.addGoods(goods);
//        System.out.println("主键回填：" + goods.getId());

        //调用索引工程同步索引库 - URL - HttpClient
        //传参：id
        //传参：对象json
        HttpClientUtil.sendJsonPost("http://localhost:8082/solr/add", new Gson().toJson(goods));
        HttpClientUtil.sendJsonPost("http://localhost:8083/item/createhtml", new Gson().toJson(goods));

        return "redirect:/goods/goodslist";
    }

//使用jsonp的跨域方法
//    @RequestMapping("/querynew")
//    @ResponseBody
//    public String queryNewGoods(){
//        List<Goods> goods = goodsService.queryNew();
//        return "hello('" + new Gson().toJson(goods) + "')";
//    }


    @RequestMapping("/querynew")
    @ResponseBody
    @CrossOrigin
    public List<Goods> queryNewGoods(){
        List<Goods> goods = goodsService.queryNew();
        System.out.println("最新的4件商品：" + goods);
        return goods;
    }
}
