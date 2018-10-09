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
public class KillLeguans2ServiceImpl implements IKillService {

    @Autowired
    private IKillDao killDao;

    @Override
    public Kill queryKillInfo(Integer gid) {
        return killDao.queryOne(gid);
    }

    @Override
    @Transactional
    public int miaosha(Integer gid, Integer number, Integer uid) {

        int result = killDao.updateSave(gid, number, 1);
        if(result > 0 ){
            //下单
            Orders orders = new Orders();
            orders.setOrderid(UUID.randomUUID().toString());
            orders.setOprice(10000.0);
            orders.setUid(uid);
            orders.setOrdertime(new Date());

            killDao.addOrder(orders);
            return 1;
        }

        System.out.println("库存不足！！！");

        return 0;
    }
}
