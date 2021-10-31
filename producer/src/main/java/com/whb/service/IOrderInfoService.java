package com.whb.service;

import com.whb.model.OrderInfoEntity;

import java.util.List;

public interface IOrderInfoService {
    OrderInfoEntity saveOrder(OrderInfoEntity orderInfoEntity);

    OrderInfoEntity queryOrderById(OrderInfoEntity orderInfoEntity);

    OrderInfoEntity updateOrder(OrderInfoEntity orderInfoEntity);
}
