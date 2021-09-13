package com.whb.util;

import com.whb.controller.RabbitProcuderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

@Component
public class LettuceLock  {
    private static final Long SUCCESS = 1L;
    private final static String LOCK = "lock";
    //删除key的lua脚本，先比较requestId是否相等，相等则删除
    private static final String DEL_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private static final Long UNLOCK_SUCCESS_RESULT = 1L;
    @Autowired
    private LettuceRedisOperation operation;
    public void lock(String uuid) {
        while(tryLock(uuid)){
            String value = operation.getValue(LOCK);
            System.out.println(RabbitProcuderController.m.get(value)+ ",其他线程加锁，尝试重新加锁！");
        }
        System.out.println("加锁成功！");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock(String uuid) {
        return operation.add(LOCK,uuid);
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public boolean unlock(String uuid) {
        Long result = operation.del(Collections.singletonList(LOCK),uuid,RedisScript.of(DEL_SCRIPT,Long.class));//指定类型，因为redis返回的int类型对应java的long
        return UNLOCK_SUCCESS_RESULT.equals(result);
    }

    public Condition newCondition() {
        return null;
    }
}
