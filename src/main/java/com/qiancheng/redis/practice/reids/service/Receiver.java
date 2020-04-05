package com.qiancheng.redis.practice.reids.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Receiver {

    private CountDownLatch latch;

    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMsg(String msg) {
        log.info("receiveMsg :" + msg);
    }
}
