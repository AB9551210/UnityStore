package com.untiy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//指定项目中Mapper接口路径的位置
@MapperScan("com.untiy.store.mapper")
public class UntiyStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(UntiyStoreApplication.class, args);
    }
}
