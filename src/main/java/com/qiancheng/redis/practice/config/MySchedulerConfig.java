package com.qiancheng.redis.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
//@EnableScheduling
public class MySchedulerConfig implements SchedulingConfigurer {
    private static AtomicInteger num = new AtomicInteger(1);

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduledThreadPool());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor scheduledThreadPool() {
        return new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 * 2,
                new ThreadFactory() {

                    @Override
                    public Thread newThread(Runnable r) {

                        Thread t = new Thread(r, "job-thread");
                        System.out.println("起一个线程" + num.getAndIncrement() + "|" + t.getId());

                        return t;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("当前任务执行失败：" + r);
                    }
                });
    }


}
