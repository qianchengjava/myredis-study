package com.qiancheng.redis.practice.nettyStudy03.standard;

import com.qiancheng.redis.practice.nettyStudy03.ChannelInit;
import com.qiancheng.redis.practice.nettyStudy03.MyInHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClient {
    public static void main(String[] args) {
        try {
            NioEventLoopGroup group = new NioEventLoopGroup(1);
            Bootstrap bs = new Bootstrap();
            ChannelFuture connect = bs.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                                 @Override
                                 protected void initChannel(SocketChannel ch) throws Exception {
                                     ch.pipeline().addLast(new MyInHandler());
                                 }
                             }
                    )
                    .connect(new InetSocketAddress("127.0.0.1", 9090));
            Channel client = connect.sync().channel();

            ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
            ChannelFuture send = client.writeAndFlush(buf);
            send.sync();

            client.closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
