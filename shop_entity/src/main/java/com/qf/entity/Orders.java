package com.qf.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Orders implements Serializable{

    private Integer id;
    private String orderid;
    private Integer uid;
    private String person;
    private String address;
    private String phone;
    private Integer code;
    private Double oprice;
    private Integer status;
    private Date ordertime;

    private List<OrderDetils> ods;
}
