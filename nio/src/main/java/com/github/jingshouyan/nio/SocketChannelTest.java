package com.github.jingshouyan.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author jingshouyan
 * #date 2021/2/11 21:10
 */
public class SocketChannelTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress(6666);
        ssc.bind(isa);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while(true) {
            SocketChannel channel = ssc.accept();
            while (channel.read(buf)!=-1){
                System.out.println(new String(buf.array()));
                buf.clear();
            }
        }
    }
}
