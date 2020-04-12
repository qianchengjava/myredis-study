package com.qiancheng.redis;

import com.qiancheng.redis.practice.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    class WaitCondition {
        boolean isOpen = false;

        public void setOpen() {
            isOpen = true;
        }

    }

    WaitCondition waitCondition = new WaitCondition();

    /**
     * 模拟死锁
     */
    @PostConstruct
    public void deadLock() {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "得到lock1");
                    Thread.sleep(3000L);
                    System.out.println("state:" + Thread.currentThread().getState());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "得到lock2");
                }
            }
        }, "线程一").start();

        new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName() + "得到lock2");
                    Thread.sleep(3000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "得到lock1");
                }
            }
        }, "线程二").start();

        new Thread(() -> {
            synchronized (waitCondition) {
                while (!waitCondition.isOpen) {
                    try {
                        waitCondition.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程3").start();

        new Thread(() -> {
            synchronized (waitCondition) {
                while (!waitCondition.isOpen) {
                    try {
                        waitCondition.wait(1000 * 60 * 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程4").start();


    }

    @PostConstruct
    public void visualVM() throws InterruptedException {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (int j = 0; j < 1000; j++) {
            Thread.sleep(10000);
            while (i < 2000000) {
                i++;
                try {
                    map.put(i, i);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

    }


}
