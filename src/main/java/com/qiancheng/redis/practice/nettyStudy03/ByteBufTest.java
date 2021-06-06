package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.buffer.ByteBuf;
import org.testng.annotations.Test;

public class ByteBufTest {
    @Test
    public void test01() {
        //        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
//        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer(8, 20);
//        MyNetty.print(buf);
//        System.out.println("##########");
//        for (int i = 0; i < 3; i++) {
//            buf.writeBytes(new byte[]{1, 2, 3, 4, 5});
//            MyNetty.print(buf);
//            System.out.println("----------");
//        }


    }


//    public static void main(String[] args) {
//        NioEventLoopGroup selectorGroup = new NioEventLoopGroup(2);
//        selectorGroup.execute(() -> {
//            try {
//                int i = 0;
//                while (true) {
//                    System.out.println("hello world");
//                    Thread.sleep(1000);
//                    i++;
//                    if (i > 4) {
//                        break;
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//
//        selectorGroup.execute(() -> {
//            try {
//                for (; ; ) {
//                    Thread.sleep(1000);
//                    System.out.println("hello world222");
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//
//    }

    //    public static void main(String[] args) {
//        try {
//            NioEventLoopGroup loopGroup = new NioEventLoopGroup(1);
//
//
//            //客户端模式
//            NioSocketChannel client = new NioSocketChannel();
//
//            loopGroup.register(client);
//
//            ChannelPipeline p = client.pipeline();
//            p.addLast(new MyInHandler());
//            //reactor 异步的特征
//            ChannelFuture connect = client.connect(new InetSocketAddress("127.0.0.1", 9090));
//            ChannelFuture sync = connect.sync();
//
//            //
//            ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
//            ChannelFuture send = client.writeAndFlush(buf);
//            send.sync();
//
//            //
//
//
//            //
//            sync.channel().closeFuture().sync();
//
//            System.out.println("client over....");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

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
