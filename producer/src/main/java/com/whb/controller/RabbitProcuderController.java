package com.whb.controller;

import com.whb.model.UserEntity;
import com.whb.util.LettuceRedisOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/redisLock")
public class RabbitProcuderController {
    private Logger logger = LoggerFactory.getLogger(RabbitProcuderController.class);
    @Autowired
    private LettuceRedisOperation operation;

    @ResponseBody
    @RequestMapping("/lock")
    public String fanoutSend(@RequestBody List<UserEntity> userEntities){
        String opt = "ok";
        operation.add();
        return opt;
    }

}
