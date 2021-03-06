package com.github.jingshouyan.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author jingshouyan
 * #date 2021/2/12 9:56
 */
public class NIOServer {

    public static final int TIMEOUT = 3000;
    public static final int CAPACITY = 1024;

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(7777);
        ssc.bind(address);
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println("服务等待3秒,没有连接");
                continue;
            }
            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(CAPACITY));
                    key.cancel();
                    continue;
                }
                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    sc.read(buf);
                    String msg = new String(buf.array(),0,buf.position(), StandardCharsets.UTF_8);
                    System.out.println("from client : " + msg);
                    buf.flip();
                    sc.write(buf);
                    buf.clear();
                }
            }
        }

    }
}
