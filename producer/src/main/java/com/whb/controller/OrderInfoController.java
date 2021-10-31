package com.whb.controller;

import com.whb.model.OrderInfoEntity;
import com.whb.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    private IOrderInfoService orderInfoService;
    @ResponseBody
    @RequestMapping("/saveOrder")
    public OrderInfoEntity saveOrder(@RequestBody OrderInfoEntity orderInfoEntity){
        return orderInfoService.saveOrder(orderInfoEntity);
    }

    @ResponseBody
    @RequestMapping("/updateOrder")
    public OrderInfoEntity updateOrder(@RequestBody OrderInfoEntity orderInfoEntity){
        return orderInfoService.updateOrder(orderInfoEntity);
    }


    @ResponseBody
    @RequestMapping("/queryOrderById")
    public OrderInfoEntity queryOrder(@RequestBody OrderInfoEntity orderInfoEntity){
        return orderInfoService.queryOrderById(orderInfoEntity);
    }
}
