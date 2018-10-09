package com.qf.shop.shop_kill.controller;

import com.qf.entity.Kill;
import com.qf.shop.shop_kill.service.IKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kill")
public class KillController {

    @Autowired
    private IKillService killService;

    /**
     * 查询秒杀的商品信息
     * @return
     */
    @RequestMapping("/queryinfo")
    public String queryKillInfo(Integer gid, Model model){
        Kill kill = killService.queryKillInfo(gid);
        System.out.println("秒杀的商品信息：" + kill);
        model.addAttribute("kill", kill);
        return "miaoshainfo";
    }

    /**
     * 开始抢购
     * @param gid
     * @param number
     * @return
     */
    @RequestMapping("/miaosha")
    @ResponseBody
    public String miaosha(Integer gid, Integer number){
        for(int i = 0; i < 15000; i++){
            new Thread(){
                @Override
                public void run() {
                    killService.miaosha(gid, number, 1);
                }
            }.start();
        }
        return "ok";
    }

}
