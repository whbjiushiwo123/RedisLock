package com.whb.service.impl;

import com.whb.service.IBusiness;
import com.whb.util.LettuceLock;
import com.whb.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class Business implements IBusiness {

    @Autowired
    private LettuceLock lock;
    public void add(String uuid) throws InterruptedException {
        try{
            System.out.println(Thread.currentThread().getName()+"，加锁！");
            lock.lock(uuid);
            System.out.println(Thread.currentThread().getName()+"，业务执行成功！");
        }catch (Exception e){
            System.out.println("加锁异常了:"+e);
            e.printStackTrace();
        }finally {
            lock.unlock(uuid);
            System.out.println(Thread.currentThread().getName()+"，解锁成功，退出！");
        }
    }
}