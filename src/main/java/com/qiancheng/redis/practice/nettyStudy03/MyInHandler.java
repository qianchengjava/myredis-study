package com.qiancheng.redis.practice.nettyStudy03;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 就是用户自己实现的，你能说让用户放弃属性的操作吗
 * @ChannelHandler.Sharable 不应该被强压给coder
 */
//@ChannelHandler.Sharable
public class MyInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client  channelRegistered ... ");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println("client  channelActive ... ");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
//        CharSequence charSequence = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
        CharSequence charSequence = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(charSequence);
        ctx.writeAndFlush(buf);
    }


}