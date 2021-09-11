package org.example;

import com.alibaba.fastjson.JSON;
import com.whb.service.IBusiness;
import com.whb.util.LettuceLock;
import com.whb.util.LettuceRedisOperation;
import com.whb.util.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试

@ContextConfiguration(locations={"classpath:testApplication.xml"}) //加载配置文件
public class AppTest {
    @Autowired
    private IBusiness business;
    public static Map m = new HashMap<>();
    @Test
    public void test02(){
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String uuid = UUIDUtils.getUUID();
                        m.put(uuid,Thread.currentThread().getName());
                        business.add(uuid);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
