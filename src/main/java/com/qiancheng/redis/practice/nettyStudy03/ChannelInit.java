package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.channel.*;

@ChannelHandler.Sharable
public class ChannelInit extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("ChannelInit  channelRegistered ... ");
        Channel client = ctx.channel();
        ChannelPipeline p = client.pipeline();
        p.addLast(new MyInHandler());//2,client::pipline[ChannelInit,MyInHandler]
        ctx.pipeline().remove(this);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println("ChannelInit   channelActive ... ");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("channelInit  read....");
    }


}
