package com.qf.shop.shop_service_impl.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Cart;
import com.qf.service.ICartService;
import com.qf.shop.dao.ICartDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartDao cartDao;

    @Override
    public List<Cart> queryAllByUid(Integer uid) {
        return cartDao.queryAllByUid(uid);
    }

    @Override
    public int addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    @Override
    public int deleteCart(Integer id) {
        return cartDao.deleteCart(id);
    }

    @Override
    public int deleteAllCart(Integer uid) {
        return cartDao.deleteAllCart(uid);
    }
}
