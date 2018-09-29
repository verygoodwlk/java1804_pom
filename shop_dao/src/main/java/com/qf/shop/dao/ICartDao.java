package com.qf.shop.dao;

import com.qf.entity.Cart;

import java.util.List;

public interface ICartDao {

    List<Cart> queryAllByUid(Integer uid);

    int addCart(Cart cart);

    int deleteCart(Integer id);

    int deleteAllCart(Integer uid);
}
