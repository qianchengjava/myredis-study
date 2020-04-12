package com.qiancheng.redis.practice.jvm;

public class TestEscape2 {

    public static Object obj;

    public void variableEscape() {
        obj = new Object();//发生逃逸
    }

    public Object methodEscape() {
        return new Object();//方法逃逸
    }

    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

    /**
     * -XX:+DoEscapeAnalysis 进行栈上分配，很快6ms
     *
     * -XX:-DoEscapeAnalysis  进行堆分配，很慢几百ms
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
