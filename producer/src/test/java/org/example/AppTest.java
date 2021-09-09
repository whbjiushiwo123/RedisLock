package org.example;

import com.alibaba.fastjson.JSON;
import com.whb.service.Business;
import com.whb.util.LettuceLock;
import com.whb.util.LettuceRedisOperation;
import com.whb.util.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试

@ContextConfiguration(locations={"classpath:testApplication.xml"}) //加载配置文件
public class AppTest {
    @Autowired
    private LettuceLock lock;
    @Test
    public void test02(){
        for(int i=0;i<10;i++){
        }

    }


}
