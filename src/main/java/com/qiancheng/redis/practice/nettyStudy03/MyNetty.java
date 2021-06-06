package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.testng.annotations.Test;

import java.net.InetSocketAddress;


public class MyNetty {


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
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();

        thread.register(server);

        //指不定什么时候发生事件
        ChannelPipeline p = server.pipeline();
        p.addLast(new MyAcceptHandler(thread, new MyInHandler()));


        ChannelFuture bind = server.bind(new InetSocketAddress("127.0.0.1", 9090));
        bind.sync().channel().closeFuture().sync();
        System.out.println("server close .... ");

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




class MyAcceptHandler extends ChannelInboundHandlerAdapter {


    private final EventLoopGroup selector;
    private final ChannelHandler handler;

    public MyAcceptHandler(EventLoopGroup thread, ChannelHandler myInHandler) {
        this.selector = thread;
        this.handler = myInHandler;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" server channelRegistered ... ");
    }


    /**
     * listen socket 只能读到accept client
     * socket     R/W
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocketChannel client = (SocketChannel) msg;//accept 我怎么没调用额？
        //1.注册
        selector.register(client);

        //2.响应式的  handler
        ChannelPipeline p = client.pipeline();
        p.addLast(handler);
    }

}