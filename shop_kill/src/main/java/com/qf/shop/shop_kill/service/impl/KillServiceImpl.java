package com.qf.shop.shop_kill.service.impl;

import com.qf.entity.Kill;
import com.qf.entity.Orders;
import com.qf.shop.shop_kill.dao.IKillDao;
import com.qf.shop.shop_kill.service.IKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class KillServiceImpl implements IKillService {

    @Autowired
    private IKillDao killDao;

    @Override
    public Kill queryKillInfo(Integer gid) {
        return killDao.queryOne(gid);
    }

    @Override
    public synchronized int miaosha(Integer gid, Integer number, Integer uid) {
//
//        //查看库存
//        Kill kill = killDao.queryOne(gid);
//        if(kill.getSave() >= number){
//            //扣减库存
//            killDao.updateSave(gid, number);
//
//            //下单
//            Orders orders = new Orders();
//            orders.setOrderid(UUID.randomUUID().toString());
//            orders.setOprice(kill.getPrice() * number);
//            orders.setUid(uid);
//            orders.setOrdertime(new Date());
//
//            killDao.addOrder(orders);
//
//            return 1;
//        }
//
//        System.out.println("库存不足！！！");

        return 0;
    }
}
