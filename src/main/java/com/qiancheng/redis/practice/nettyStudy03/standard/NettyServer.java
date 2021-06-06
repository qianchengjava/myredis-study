package com.qiancheng.redis.practice.nettyStudy03.standard;

import com.qiancheng.redis.practice.nettyStudy03.MyInHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void main(String[] args) {
        try {
            NioEventLoopGroup group = new NioEventLoopGroup(1);
            ServerBootstrap bs = new ServerBootstrap();
            ChannelFuture bind = bs.group(group, group).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyInHandler());
                        }
                    })
                    .bind(new InetSocketAddress("127.0.0.1", 9090));
            bind.sync().channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
