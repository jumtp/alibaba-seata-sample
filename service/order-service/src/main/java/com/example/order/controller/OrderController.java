package com.example.order.controller;

import com.example.common.TableId;
import com.example.order.OrderInfo;
import com.example.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public TableId createOrder(@RequestBody OrderInfo orderInfo){
        return orderService.createOrder(orderInfo);
    }
}
