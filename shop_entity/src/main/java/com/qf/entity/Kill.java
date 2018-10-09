package com.qf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class Kill implements Serializable{

    private Integer id;
    private String title;
    private String image;
    private double price;
    private Integer save;
    private Date starttime;
    private Date endtime;
    private Integer statu;
    private Integer version;
}
