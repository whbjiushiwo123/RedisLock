package com.whb.service;

import com.whb.util.LettuceLock;
import com.whb.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business implements Runnable{
    String uuid;
    @Autowired
    private LettuceLock lock;

    public Business(){
        this.uuid = UUIDUtils.getUUID();
    }
    @Override
    public void run() {
        add(uuid);
    }
    public void add(String uuid){
        lock.lock(uuid);
        try{
            System.out.println(Thread.currentThread().getName()+"，加锁成功，执行业务代码！");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName()+"，业务执行成功！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(uuid);
            System.out.println(Thread.currentThread().getName()+"，解锁成功，退出！");
        }
    }
}