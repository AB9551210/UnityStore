package com.untiy.store.entity;

import lombok.Data;

import java.io.Serializable;

/** 购物车数据的实体类 */
@Data
public class Cart extends BaseEntity implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Double price;
}
