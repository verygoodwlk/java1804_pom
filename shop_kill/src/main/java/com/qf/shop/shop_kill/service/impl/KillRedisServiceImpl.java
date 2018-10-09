package com.qf.shop.shop_kill.service.impl;

import com.google.gson.Gson;
import com.qf.entity.Kill;
import com.qf.entity.Orders;
import com.qf.shop.shop_kill.dao.IKillDao;
import com.qf.shop.shop_kill.service.IDBService;
import com.qf.shop.shop_kill.service.IKillService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@Primary
public class KillRedisServiceImpl implements IKillService {

    @Autowired
    private IKillDao killDao;

    @Autowired
    private IDBService dbService;

    @Autowired
    private RedisTemplate redisTemplate;

    private RedisConnection redisConnection;

    private String sha1;

    @PostConstruct
    public void init(){
        redisConnection = redisTemplate.getConnectionFactory().getConnection();

        String path = this.getClass().getResource("/").getPath() + "static/lua/kill.lua";
        File file = new File(path);
        byte[] buffer = null;
        try {
            buffer = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //缓存lua脚本
        sha1 = redisConnection.scriptLoad(buffer);
    }

    @Override
    public Kill queryKillInfo(Integer gid) {
        return killDao.queryOne(gid);
    }

    /**
     * 通过redis实现秒杀的业务
     * @param gid
     * @param number
     * @param uid
     * @return
     */
    @Override
    public int miaosha(Integer gid, Integer number, Integer uid) {

        Orders order = new Orders();
        order.setOrdertime(new Date());
        order.setUid(uid);
        order.setOrderid(UUID.randomUUID().toString());

        String orderJson = new Gson().toJson(order);

        //直接执行lua脚本进行库存的扣减
        //返回0 - 抢购成功 1 - 库存不足 2 - 库存为空
        long result = redisConnection.evalSha(
                sha1,
                ReturnType.INTEGER,
                1,
                (gid + "").getBytes(),
                (number + "").getBytes(),
                orderJson.getBytes());


        if(result == 2){
            //将redis的数据同步到数据库
            dbService.syncDataBase(gid);
        }
        return (int) result;
    }
}
