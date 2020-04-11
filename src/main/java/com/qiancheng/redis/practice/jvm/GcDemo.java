package com.qiancheng.redis.practice.jvm;

/**
 * -verbose:gc -XX:+PrintGCDetails
 */
public class GcDemo {
    public Object instance = null;
    private static final int num = 1024 * 1024;
    private byte[] bigSize = new byte[5 * num];

    public static void main(String[] args) {
        GcDemo gcDemo1 = new GcDemo();
        GcDemo gcDemo2 = new GcDemo();
        gcDemo1.instance = gcDemo2;
        gcDemo2.instance = gcDemo1;
        gcDemo1 = null;
        gcDemo2 = null;
        System.gc();
    }
}
