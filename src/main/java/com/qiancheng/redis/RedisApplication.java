package com.qiancheng.redis;

import com.qiancheng.redis.practice.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisApplication {
    /**
     * aaa
     *
     * @param args
     */
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(RedisApplication.class, args);
        AppConfig appConfig = ctx.getBean(AppConfig.class);
        System.out.println(appConfig.getName());
    }

}
