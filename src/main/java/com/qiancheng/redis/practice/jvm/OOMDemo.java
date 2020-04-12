package com.qiancheng.redis.practice.jvm;

/**
 * -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/qiancheng
 */
public class OOMDemo {
    public static void main(String[] args) {
        String name = "ababababaewsdf";
        for (int i = 0; i < 10000000; i++) {
            name += name;
        }
        System.out.println(name);
    }

}
