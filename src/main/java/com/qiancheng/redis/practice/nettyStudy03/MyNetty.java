package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.testng.annotations.Test;


public class MyNetty {





    @Test
    public void test01(){
        //        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        MyNetty.print(buf);
        System.out.println("##########");
        for (int i = 0; i < 6; i++) {
            buf.writeBytes(new byte[]{1, 2, 3, 4,5});
            MyNetty.print(buf);
            System.out.println("----------");
        }
    }


    public static void print(ByteBuf buf) {
        System.out.println("buf.isReadable()" + ":" + buf.isReadable());
        System.out.println("readerIndex" + ":" + buf.readerIndex());
        System.out.println("readableBytes" + ":" + buf.readableBytes());
        System.out.println("isWritable" + ":" + buf.isWritable());
        System.out.println("writerIndex" + ":" + buf.writerIndex());
        System.out.println("writableBytes" + ":" + buf.writableBytes());
        System.out.println("maxCapacity" + ":" + buf.maxCapacity());
        System.out.println("isDirect" + ":" + buf.isDirect());
    }

}
