package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class MyNettyClient {
    public static void main(String[] args) {
        try {
            NioEventLoopGroup loopGroup = new NioEventLoopGroup(1);


            //客户端模式
            NioSocketChannel client = new NioSocketChannel();

            loopGroup.register(client);

            ChannelPipeline p = client.pipeline();
            p.addLast(new MyInHandler());
            //reactor 异步的特征
            ChannelFuture connect = client.connect(new InetSocketAddress("127.0.0.1", 9090));
            ChannelFuture sync = connect.sync();

            //
            ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
            ChannelFuture send = client.writeAndFlush(buf);
            send.sync();

            //


            //
            sync.channel().closeFuture().sync();

            System.out.println("client over....");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
