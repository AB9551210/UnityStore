package com.untiy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("com.untiy.store.mapper")
public class UntiyStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(UntiyStoreApplication.class, args);
    }
}
