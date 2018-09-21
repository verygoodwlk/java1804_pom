package com.qf.entity;


import lombok.*;

import java.io.Serializable;

/**
 * 商品实体类 - lombok
 * 1、在idea上安装lombok的插件
 * 2、在工程中添加依赖
 *
 <dependency>
 <groupId>org.projectlombok</groupId>
 <artifactId>lombok</artifactId>
 <version>1.18.2</version>
 <scope>provided</scope>
 </dependency>

   3、在实体类上添加响应的注解就可以了
 *
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable{

    private Integer id;
    private String title;
    private String ginfo;
    private double gcount;
    private Integer tid = 1;
    private double allprice;
    private double price;
    private String gimage;

}
