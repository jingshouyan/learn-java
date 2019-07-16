package com.github.jingshouyan.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    AtomicInteger i = new AtomicInteger(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        log.info("client received: {}", msg.toString(CharsetUtil.UTF_8));
        int count = i.getAndIncrement();
        ByteBuf buf = Unpooled.copiedBuffer("client send " + count, CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
        if(count > 200){
            ctx.close();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("client error", cause);
        ctx.close();
    }
}
