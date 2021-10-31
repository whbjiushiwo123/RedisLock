package com.whb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whb.dao.OrderMapper;
import com.whb.model.OrderInfoEntity;
import com.whb.service.IOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderInfoService implements IOrderInfoService {
    private Logger logger = LoggerFactory.getLogger(OrderInfoService.class);
    private final static String ORDER_SAVE_EXCHANGE = "order-save-exchange";
    private final static String ORDER_SAVE_ROUTEKEY = "order";
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    @CachePut(value = "order", key = "#orderInfoEntity.id", unless = "#result eq null")
    public OrderInfoEntity saveOrder(OrderInfoEntity orderInfoEntity) {
        logger.info("开始发送消息------------------- start："+JSONObject.toJSONString(orderInfoEntity));
        orderMapper.saveOrder(orderInfoEntity);

        rabbitTemplate.convertAndSend(ORDER_SAVE_EXCHANGE,ORDER_SAVE_ROUTEKEY,
                new Message(JSONObject.toJSONString(orderInfoEntity).getBytes(),new MessageProperties()));

        logger.info("end-------------------消息发送结束！");
        return orderInfoEntity;
    }

    @Override
    @Cacheable(value = "order", key = "#orderInfoEntity.id", unless = "#result eq null")
    public OrderInfoEntity queryOrderById(OrderInfoEntity orderInfoEntity) {
        return orderMapper.queryOrderById(orderInfoEntity.getId());
    }

    @CacheEvict(value = "order", key = "#orderInfoEntity.id")
    public OrderInfoEntity updateOrder(OrderInfoEntity orderInfoEntity){
        orderMapper.orderMapper(orderInfoEntity);
        return orderInfoEntity;
    }

}
