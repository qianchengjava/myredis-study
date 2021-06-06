package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ChannelInit extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelInit  channelRegistered ... ");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println("ChannelInit   channelActive ... ");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }


}
