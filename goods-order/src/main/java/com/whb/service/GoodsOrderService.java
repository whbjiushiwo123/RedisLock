package com.whb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class GoodsOrderService {
    private Logger logger = LoggerFactory.getLogger(GoodsOrderService.class);
    @RabbitListener(queues = "order-save")
    @RabbitHandler
    public void onMessage(String message) {
        logger.info("accept sms ready process……");
        logger.info("收到消息："+message);
    }
}
