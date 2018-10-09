package com.qf.shop.shop_kill.service.impl;

import com.google.gson.Gson;
import com.qf.entity.Orders;
import com.qf.shop.shop_kill.dao.IKillDao;
import com.qf.shop.shop_kill.service.IDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBServiceImpl implements IDBService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IKillDao killDao;

    @PostConstruct
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    /**
     * 同步数据库 - 异步
     * @param gid
     * @return
     */
    @Override
    @Async
    @Transactional
    public void syncDataBase(Integer gid) {
        System.out.println("同步数据库");

        //获得redis的库存
        Integer save = Integer.parseInt((String) redisTemplate.opsForValue().get("kill" + gid));
        killDao.updateSaveNumber(gid, save);

        //获得redis的订单
        long size = redisTemplate.opsForList().size("order" + gid);
        List<String> orderList = redisTemplate.opsForList().range("order" + gid, 0, size);

        List<Orders> orderslist = new ArrayList<>();
        for(int i = 0; i < orderList.size(); i++){
            //json -> Orders
            Orders orders = new Gson().fromJson(orderList.get(i), Orders.class);
            orderslist.add(orders);

            if(i % 5000 == 0 || i == orderList.size() - 1){
                //添加到数据库
                killDao.addOrders(orderslist);
                orderslist.clear();
            }
        }

        //删除redis
        redisTemplate.delete("kill" + gid);
        redisTemplate.delete("order" + gid);

        System.out.println("数据库同步完成");
    }
}
