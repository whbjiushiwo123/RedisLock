package com.whb.controller;

import com.alibaba.fastjson.JSONObject;
import com.whb.model.UserEntity;
import com.whb.service.IBusiness;
import com.whb.util.LettuceRedisOperation;
import com.whb.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.util.UIUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/redisLock")
public class RabbitProcuderController {
    private Logger logger = LoggerFactory.getLogger(RabbitProcuderController.class);
    @Autowired
    private IBusiness business;
    public static Map m = new HashMap<>();
    @ResponseBody
    @RequestMapping("/lock")
    public String fanoutSend(@RequestBody List<UserEntity> userEntities) throws InterruptedException {
        System.out.println("开始："+ JSONObject.toJSONString(userEntities));
        String opt = "ok";
        String uuid = UUIDUtils.getUUID();
        m.put(uuid,Thread.currentThread().getName());
        business.add(uuid);
        return opt;
    }

}
