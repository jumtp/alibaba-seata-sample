package com.example.order.service;

import com.example.common.TableId;
import com.example.order.OrderInfo;

public interface IOrderService {
    TableId createOrder(OrderInfo orderInfo);
}
