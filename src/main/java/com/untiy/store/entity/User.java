package com.untiy.store.entity;

import lombok.Data;

import java.io.Serializable;

//用户实体类
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid ;
    private String username;
    private String password;
    private String salt;
    private String personName;
    private String email;
    private String avatar;
    private Integer isDelete;
}
