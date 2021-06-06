package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;

public class MyAcceptHandler extends ChannelInboundHandlerAdapter {


    private final EventLoopGroup selector;
    private final ChannelHandler handler;

    public MyAcceptHandler(EventLoopGroup thread, ChannelHandler channelHandler) {
        this.selector = thread;
        this.handler = channelHandler;//ChannelInit
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

        /**
         * listen socket 只能读到accept client
         * socket     R/W
         */
        SocketChannel client = (SocketChannel) msg;//accept 我怎么没调用额？

        //2.响应式的  handler
        ChannelPipeline p = client.pipeline();//1,client::pipeline
        p.addLast(handler);

        //1.注册
        selector.register(client);
    }

}