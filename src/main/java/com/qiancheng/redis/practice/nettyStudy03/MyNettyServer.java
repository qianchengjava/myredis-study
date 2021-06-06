package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


public class MyNettyServer {


    public static void main(String[] args) throws Exception {


        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();

        thread.register(server);

        //指不定什么时家里来人。。响应式
        ChannelPipeline p = server.pipeline();
        p.addLast(new MyAcceptHandler(thread, new ChannelInit()));
        //p.addLast(new MyAcceptHandler(thread, new MyInHandler()));


        ChannelFuture bind = server.bind(new InetSocketAddress("127.0.0.1", 9090));
        bind.sync().channel().closeFuture().sync();
        System.out.println("server close .... ");

    }


}




