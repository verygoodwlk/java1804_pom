package com.qf.shop.dao;

import com.qf.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICartDao {

    List<Cart> queryAllByUid(Integer uid);

    int addCart(Cart cart);

    int deleteCart(Integer id);

    int deleteAllCart(Integer uid);

    List<Cart> queryCartByGids(@Param("gid") Integer[] gid, @Param("uid") Integer uid);

    List<Cart> queryByCids(@Param("cids") Integer[] cids);
}
