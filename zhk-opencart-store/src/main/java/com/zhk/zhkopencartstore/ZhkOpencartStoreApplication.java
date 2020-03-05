package com.zhk.zhkopencartstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhk.zhkopencartstore.dao")
public class ZhkOpencartStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhkOpencartStoreApplication.class, args);
    }

}
