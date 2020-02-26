package com.zhk.zhkopencart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhk.zhkopencart.dao")
public class ZhkOpencartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhkOpencartApplication.class, args);
    }

}
