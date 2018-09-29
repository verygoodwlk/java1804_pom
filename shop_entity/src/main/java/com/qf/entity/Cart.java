package com.qf.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Cart implements Serializable{

    private Integer id;
    private Integer gid;//商品id
    private Integer gnumber;//商品的数量
    private Integer uid;//用户的id

    private Goods goods;
}
