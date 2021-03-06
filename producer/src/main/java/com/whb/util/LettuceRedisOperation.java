package com.whb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class LettuceRedisOperation {
    @Autowired
    private RedisTemplate redisTemplate;
    public boolean add(String key,String value){
        return redisTemplate.opsForValue().setIfPresent(key,value);
    }
    public boolean addExpire(String key,String value){
        return redisTemplate.opsForValue().setIfPresent(key,value,10000, TimeUnit.MILLISECONDS);
    }
    public long del(List<String> keys, String value, RedisScript<Long> script){
         return (long)redisTemplate.execute(script,keys,value);
    }

    public String getValue(String key){
        return (String)redisTemplate.opsForValue().get(key);
    }

}
