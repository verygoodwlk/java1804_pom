package com.qf.service;

import com.qf.entity.Orders;

import java.util.List;

public interface IOrderService {

    String addOrderAndOrderDetils(Integer[] cids, Integer aid, Integer uid);

    List<Orders> queryByUid(Integer uid);

    Orders queryByOrderId(String orderid);

    int updateStatusByOrderId(String orderid, int status);
}
