package com.github.jingshouyan.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import lombok.SneakyThrows;

import java.util.stream.IntStream;

public class EchoClient {

    private final String host = "127.0.0.1";
    private final int port = 7070;

    @SneakyThrows
    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup group1 = new OioEventLoopGroup();
        EchoClientHandler clientHandler = new EchoClientHandler();
        try{
            Bootstrap bootstrap =  new Bootstrap();
            bootstrap
                    .group(group)
                    .channel(NioSocketChannel.class)
//                    .group(group1)
//                    .channel(OioSocketChannel.class)
                    .remoteAddress(host,port)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        IntStream.range(0,1000)
                .parallel()
                .forEach(i-> echoClient.start());
    }
}
