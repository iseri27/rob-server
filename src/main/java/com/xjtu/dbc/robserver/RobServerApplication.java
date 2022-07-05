package com.xjtu.dbc.robserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xjtu.dbc.robserver.*.dao"})
@MapperScan({"com.xjtu.dbc.robserver.*.*.dao"})
@MapperScan({"com.xjtu.dbc.robserver.*.*.*.dao"})
public class RobServerApplication {
    public static void main(String[] args) {
        123；
        SpringApplication.run(RobServerApplication.class, args);
    }

}
