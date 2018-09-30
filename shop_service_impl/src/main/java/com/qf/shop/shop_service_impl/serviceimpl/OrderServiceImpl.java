package com.qf.shop.shop_service_impl.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Address;
import com.qf.entity.Cart;
import com.qf.entity.OrderDetils;
import com.qf.entity.Orders;
import com.qf.service.IOrderService;
import com.qf.shop.dao.IAddressDao;
import com.qf.shop.dao.ICartDao;
import com.qf.shop.dao.IOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private ICartDao cartDao;

    @Autowired
    private IAddressDao addressDao;

    /**
     * 添加订单
     * @param cids
     * @param aid
     * @param uid
     * @return
     */
    @Override
    @Transactional
    public String addOrderAndOrderDetils(Integer[] cids, Integer aid, Integer uid) {

        //1、根据购物车的id查询购物车的列表
        List<Cart> carts = cartDao.queryByCids(cids);
        //2、根据地址的id获得收货地址的详细信息
        Address address = addressDao.queryById(aid);

        //计算下订单总价格
        double allprice = 0;
        for(Cart cart : carts){
            allprice += cart.getGnumber() * cart.getGoods().getPrice();
        }

        //2、根据购物车列表生成订单和订单详情对象
        Orders orders = new Orders();
        orders.setOrderid(UUID.randomUUID().toString());
        orders.setPerson(address.getPerson());
        orders.setAddress(address.getAddress());
        orders.setPhone(address.getPhone());
        orders.setCode(address.getCode());
        orders.setUid(uid);
        orders.setOprice(allprice);//总价格
        orders.setOrdertime(new Date());
        orders.setStatus(0);//0 - 未支付 1 - 已支付/待发货  2 - 已发货/待收货  3 - 已收货/待评价 4 - 评价/完成

        //添加订单
        orderDao.addOrder(orders);

        //订单详情
        List<OrderDetils> orderDetilsList = new ArrayList<>();
        for (Cart cart : carts) {
            OrderDetils orderDetils = new OrderDetils();
            orderDetils.setOid(orders.getId());
            orderDetils.setGid(cart.getGid());
            orderDetils.setGname(cart.getGoods().getTitle());
            orderDetils.setGinfo(cart.getGoods().getGinfo());
            orderDetils.setPrice(cart.getGoods().getPrice());
            orderDetils.setGcount(cart.getGnumber());
            orderDetils.setGimage(cart.getGoods().getGimage());
            orderDetilsList.add(orderDetils);
        }

        orderDao.addOrderDetils(orderDetilsList);

        //删除购物车
        for(Cart cart : carts){
            cartDao.deleteCart(cart.getId());
        }

        return orders.getOrderid();
    }

    @Override
    public List<Orders> queryByUid(Integer uid) {
        return orderDao.queryByUid(uid);
    }

    @Override
    public Orders queryByOrderId(String orderid) {
        return orderDao.queryByOrderId(orderid);
    }

    @Override
    public int updateStatusByOrderId(String orderid, int status) {
        return orderDao.updateStatusByOrderId(orderid, status);
    }
}
