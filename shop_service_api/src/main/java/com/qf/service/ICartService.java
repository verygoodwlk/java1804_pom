package com.qf.service;

import com.qf.entity.Cart;
import com.qf.entity.User;

import java.util.List;

public interface ICartService {

    List<Cart> queryAllByUid(Integer uid);

    int addCart(Cart cart);

    int deleteCart(Integer id);

    int deleteAllCart(Integer uid);

    List<Cart> getCarts(User user, String carts);

    List<Cart> queryCartByGids(Integer[] gid, Integer uid);
}
