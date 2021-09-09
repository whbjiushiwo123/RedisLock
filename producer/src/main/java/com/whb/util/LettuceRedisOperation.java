package com.whb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class LettuceRedisOperation {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void add(){
        redisTemplate.opsForValue().set("aa","dddd");
    }

}
