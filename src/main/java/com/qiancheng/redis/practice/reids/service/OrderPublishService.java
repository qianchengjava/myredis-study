package com.qiancheng.redis.practice.reids.service;
/**
 * 接收一个订单
 */
public interface OrderPublishService {

    /**
     * 接收一个订单
     * @param orderNo
     */
    void acceptOrder(String orderNo);

}
