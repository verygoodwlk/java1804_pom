package com.qf.shop.shop_kill.service.impl;

import com.qf.entity.Kill;
import com.qf.entity.Orders;
import com.qf.shop.shop_kill.dao.IKillDao;
import com.qf.shop.shop_kill.service.IKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class KillLeguansServiceImpl implements IKillService {

    @Autowired
    private IKillDao killDao;

    @Override
    public Kill queryKillInfo(Integer gid) {
        return killDao.queryOne(gid);
    }

    @Override
    @Transactional
    public int miaosha(Integer gid, Integer number, Integer uid) {
//        //查看库存
//        Kill kill = killDao.queryOne(gid);
//        if (kill.getSave() >= number) {
//            //扣减库存
//            int result = killDao.updateSave(gid, number, kill.getVersion());
//            if (result > 0) {
//                //下单
//                Orders orders = new Orders();
//                orders.setOrderid(UUID.randomUUID().toString());
//                orders.setOprice(kill.getPrice() * number);
//                orders.setUid(uid);
//                orders.setOrdertime(new Date());
//
//                killDao.addOrder(orders);
//                return 1;
//            } else {
//                System.out.println("抢购失败！！！");
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("库存不足！！！");

        return 0;
    }
}
