package com.qiancheng.redis.practice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan
@PropertySource("classpath:my-config.properties")
@Data
public class AppConfig {
    @Value("${qc.name}")
    private String name;
}
