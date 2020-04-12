package com.qiancheng.redis.practice.jvm;

public class JStackDemo {
    public static void main(String[] args) {
        try {
            for(int i=0;i<30000;i++){
                Thread.sleep(10);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
