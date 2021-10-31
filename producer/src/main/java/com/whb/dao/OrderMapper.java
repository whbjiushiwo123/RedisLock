package com.whb.dao;

import com.whb.model.OrderInfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int saveOrder(OrderInfoEntity orderInfoEntity);

    OrderInfoEntity queryOrderById(String id);

    int orderMapper(OrderInfoEntity orderInfoEntity);
}
