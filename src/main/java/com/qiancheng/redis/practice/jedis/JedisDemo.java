package com.qiancheng.redis.practice.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisDemo {

    public static void main(String[] args) {
        JedisUtil.init();
        Jedis jedis = JedisUtil.jedisPool.getResource();
        String res = jedis.set("qc", "ccc");
        System.out.println(res);

        jedis.incr("i");
        jedis.decr("i");
        jedis.decr("i");
        jedis.decr("i");
        jedis.decr("i");
        jedis.decr("i");
        jedis.expire("i", 3);
        System.out.println(jedis.type("i"));
        jedis.lpush("list1", "a1");
        jedis.lpush("list1", "a2");
        System.out.println(jedis.lrange("list1", 0, -1));
        //
//        for (int i = 0; i < 100; i++) {
//            jedis.sadd("set1", "m" + i);
//        }

        jedis.sadd("set1", "s1", "s2", "s3", "s4", "s5", "s6", "s7");
        Set<String> sets = jedis.smembers("set1");
        System.out.println("sets:" + sets);
        System.out.println(jedis.scard("set1"));
        System.out.println(jedis.sismember("set1", "s1"));
        String spop = jedis.spop("set1");
        System.out.println("spop:" + spop);
        JedisUtil.jedisPool.close();
    }
}
