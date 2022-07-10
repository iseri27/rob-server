package com.xjtu.dbc.robserver;

import org.bouncycastle.x509.NoSuchStoreException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RobServerApplicationTests {
    @Resource
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {

        redisTemplate.opsForValue().set("name", "西交第一白兰王");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("Name : " + name);
    }

}
