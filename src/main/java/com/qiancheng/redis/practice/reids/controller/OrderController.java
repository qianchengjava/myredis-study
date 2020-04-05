package com.qiancheng.redis.practice.reids.controller;

import com.qiancheng.redis.practice.reids.service.OrderPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderPublishService orderPublishService;

    @GetMapping("/order/{orderNo}")
    public String order(@PathVariable("orderNo") String orderNo) {
        orderPublishService.acceptOrder(orderNo);
        return orderNo + "接收ok";
    }
}
