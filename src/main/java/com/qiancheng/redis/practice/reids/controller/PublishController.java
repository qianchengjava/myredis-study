package com.qiancheng.redis.practice.reids.controller;

import com.qiancheng.redis.practice.reids.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PublishController {

    @Autowired
    private PublishService publishService;
    private int num = 0;

    @GetMapping("/publish")
    public String publish() {
        num++;
        publishService.publish("hi world " + num + "!");
        return "ok";
    }
}
