package com.qiancheng.redis;

import java.util.concurrent.atomic.AtomicInteger;

public class MyTest {

    private static volatile AtomicInteger count = new AtomicInteger(20);

    public int decrement() {
        return count.getAndDecrement();
    }


    public static void main(String[] args) throws InterruptedException {
        MyTest myTest = new MyTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MyTest.count.get() != 0) {

                        System.out.print(myTest.decrement() + "  ");
                    } else {
                        System.out.println(myTest.decrement());
                        break;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MyTest.count.get() != 0) {
                        System.out.print(myTest.decrement() + "  ");
                    } else {
                        System.out.println(myTest.decrement());
                        break;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MyTest.count.get() != 0) {
                        System.out.print(myTest.decrement() + "  ");
                    } else {
                        System.out.println(myTest.decrement());
                        break;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MyTest.count.get() != 0) {
                        System.out.print(myTest.decrement() + "  ");
                    } else {
                        System.out.println(myTest.decrement());
                        break;
                    }
                }
            }
        }).start();
    }

}
