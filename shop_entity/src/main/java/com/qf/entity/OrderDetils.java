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
public class OrderDetils implements Serializable{
    private Integer id;
    private Integer oid;
    private Integer gid;
    private String gname;
    private String ginfo;
    private double price;
    private Integer gcount;
    private String gimage;
}
