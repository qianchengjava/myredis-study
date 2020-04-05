package com.qiancheng.redis.practice.reids.service.impl;

import com.qiancheng.redis.practice.reids.service.PublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class PublishServiceImpl implements PublishService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Value("${redis.msg.topic}")
    private String myTopic;

    @Override
    public void publish(String msg) {
        log.info("publish:" + msg);
        stringRedisTemplate.convertAndSend(myTopic, msg);
    }
}
