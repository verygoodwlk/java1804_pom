package com.qf.shop.shop_kill.dao;

import com.qf.entity.Kill;
import com.qf.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IKillDao {

    Kill queryOne(Integer id);

    int updateSave(@Param("id") Integer id, @Param("number") Integer number, @Param("version") Integer version);

    int addOrder(Orders orders);

    int addOrders(@Param("orderslist") List<Orders> orders);

    int updateSaveNumber(@Param("id") Integer id, @Param("save") Integer save);
}
