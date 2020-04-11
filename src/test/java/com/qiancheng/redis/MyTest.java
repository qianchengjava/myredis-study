package com.qiancheng.redis;

import java.util.concurrent.atomic.AtomicInteger;

public class MyTest {

    private static volatile AtomicInteger count = new AtomicInteger(1500);

    public int decrement(){
        return count.getAndDecrement();
    }


    public static void main(String[] args) throws InterruptedException {
        MyTest myTest = new MyTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000; i ++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(MyTest.count.get() != 0){
                        myTest.decrement();
                        System.out.print(MyTest.count+"  ");
                    }else{
                        System.out.println(MyTest.count);
                        break;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000; i ++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(MyTest.count.get() != 0){
                        myTest.decrement();
                        System.out.print(MyTest.count+"  ");
                    }else{
                        System.out.println(MyTest.count);
                        break;
                    }
                }
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0 ; i < 1000; i ++){
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if(MyTest.count.get() != 0){
//                        myTest.decrement();
//                        System.out.print(MyTest.count+"  ");
//                    }else{
//                        System.out.println(MyTest.count);
//                        break;
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0 ; i < 1000; i ++){
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if(MyTest.count.get() != 0){
//                        myTest.decrement();
//                        System.out.print(MyTest.count+"  ");
//                    }else{
//                        System.out.println(MyTest.count);
//                        break;
//                    }
//                }
//            }
//        }).start();
    }

}
