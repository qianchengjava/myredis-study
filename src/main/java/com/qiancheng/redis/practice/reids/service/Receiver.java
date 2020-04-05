package com.qiancheng.redis.practice.reids.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class Receiver {
    /**
     * 接收订单
     *
     * @param orderNo
     */
    public void receiveOrder(String orderNo) {
        log.info("消费订单 :" + orderNo + "|的时间:" + new Date(System.currentTimeMillis()));
    }

}
