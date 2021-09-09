package com.whb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LettuceRedisOperation {
    @Autowired
    private RedisTemplate redisTemplate;
    public boolean add(String key,String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    public long del(List<String> keys, String value, RedisScript<Long> script){
         return (long)redisTemplate.execute(script,keys,value);
    }

}
