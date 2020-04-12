package com.qiancheng.redis.practice.jvm.jconsole;

import java.util.ArrayList;
import java.util.List;

public class JConsoleMemory {

    public byte[] b1 = new byte[1024 * 512];

    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allocate(10000);

    }

    private static void allocate(int n) {
        List<JConsoleMemory> jList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jList.add(new JConsoleMemory());
        }
    }
}
