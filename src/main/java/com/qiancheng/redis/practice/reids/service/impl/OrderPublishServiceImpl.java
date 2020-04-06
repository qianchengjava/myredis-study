package com.qiancheng.redis.practice.reids.service.impl;

import com.qiancheng.redis.practice.reids.service.OrderPublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * 订单service类
 */
@Slf4j
@Service
public class OrderPublishServiceImpl implements OrderPublishService {

    @Value("${redis.msg.topic}")
    private String myTopic;
    @Value("${redis.zset.delay-order.key}")
    private String delayOrderKey;
    @Value("${redis.zset.delaySeconds}")
    private int DELAY_SECONDS;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 接收一个订单
     *
     * @param orderNo
     */
    @Override
    public void acceptOrder(String orderNo) {
        log.info("当前时间:" + new Date() + "|再过" + DELAY_SECONDS + "秒之后发送订单|订单号:" + orderNo);
        zSetOperations.add(delayOrderKey, orderNo, System.currentTimeMillis() + 1000 * DELAY_SECONDS);

    }

    /**
     * 定时任务，扫描redis zset元素，取出应处理的元素并发出消息
     */
    @Scheduled(fixedRate = 500)
    public void scheduledPublishOrder() {
        System.out.println(Thread.currentThread().getName() + "|" + Thread.currentThread().getId());
        Set<ZSetOperations.TypedTuple<Object>> orderNosRes = zSetOperations.rangeByScoreWithScores(delayOrderKey, 0, Double.valueOf(System.currentTimeMillis()));
        for (ZSetOperations.TypedTuple<Object> eachOrder : orderNosRes) {
            String orderNo = (String) eachOrder.getValue();
            log.info("订单:" + orderNo + "|在" + new Date() + "从延迟队列中移除并通知给消费方");
            zSetOperations.remove(delayOrderKey, orderNo);
            stringRedisTemplate.convertAndSend(myTopic, orderNo);
        }
    }


}
