package com.qf.shop.dao;

import com.qf.entity.Goods;

import java.util.List;

public interface IGoodsDao {

    List<Goods> queryAll();

    int addGoods(Goods goods);

    List<Goods> queryNew();

    Goods queryById(Integer id);
}
