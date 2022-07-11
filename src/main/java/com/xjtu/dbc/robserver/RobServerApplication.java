package com.xjtu.dbc.robserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan({"com.xjtu.dbc.robserver.*.dao"})
@MapperScan({"com.xjtu.dbc.robserver.*.*.dao"})
@MapperScan({"com.xjtu.dbc.robserver.*.*.*.dao"})
//@EnableCaching
public class RobServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RobServerApplication.class, args);
    }

}
