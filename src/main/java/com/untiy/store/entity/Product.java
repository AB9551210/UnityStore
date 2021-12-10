package com.untiy.store.entity;

import lombok.Data;

import java.io.Serializable;

/** 商品数据的实体类 */
@Data
public class Product extends BaseEntity implements Serializable {
    private Integer id;
    private Integer line;
    private Double beforeprice;
    private Double afterprice;
    private Integer count;
    private String name;
    private String title;
    private String image;
    private Integer status;
}
