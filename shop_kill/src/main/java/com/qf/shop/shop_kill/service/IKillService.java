package com.qf.shop.shop_kill.service;

import com.qf.entity.Kill;

public interface IKillService {

    /**
     * 根据秒杀的商品id查询商品信息
     * @param gid
     * @return
     */
    Kill queryKillInfo(Integer gid);


    /**
     * 根据商品的id进行抢购业务
     * @param gid
     * @param number
     * @param uid
     * @return
     */
    int miaosha(Integer gid, Integer number, Integer uid);
}
