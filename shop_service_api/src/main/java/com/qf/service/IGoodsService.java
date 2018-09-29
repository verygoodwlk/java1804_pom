package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface IGoodsService {

    List<Goods> queryAll();

    Goods addGoods(Goods goods);

    List<Goods> queryNew();

    Goods queryById(Integer id);
}
