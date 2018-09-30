package com.qf.shop.dao;


import com.qf.entity.OrderDetils;
import com.qf.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderDao {

    int addOrder(Orders orders);

    int addOrderDetils(@Param("orderdetils") List<OrderDetils> orderDetils);

    List<Orders> queryByUid(Integer uid);


}
