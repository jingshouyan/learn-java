package com.github.jingshouyan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio client
 *
 * @author jingshouyan
 * 2021-05-07 14:32
 **/
public class NIOClient {

    public static final ExecutorService exec = Executors.newFixedThreadPool(200);

    public static void client(String ip, int port, int loop) throws IOException, InterruptedException {
        try (SocketChannel sc = SocketChannel.open()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            sc.configureBlocking(false);
            SocketAddress sa = new InetSocketAddress(ip, port);
            sc.connect(sa);

            if (sc.finishConnect()) {
                System.out.println("is open");
                for (int i = 0; i < loop; i++) {
//                    Thread.sleep(10);
                    String msg = "I'm " + i + "-th information from client";
                    buffer.clear();
                    buffer.putInt(1024 * 1024 * loop);
                    buffer.put(msg.getBytes(StandardCharsets.UTF_8));
                    buffer.flip();
                    while (buffer.hasRemaining()) {

                        sc.write(buffer);
                    }
                }
            }
            Thread.sleep(3000);
        }
    }

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 7776;
//        IntStream.range(0,100)
//                .parallel()
//                .forEach(i -> {
//                    System.out.println(i);
//                    try {
//                        client(ip, port, 1);
//                    } catch (IOException | InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
        for (int i = 0; i < 300; i++) {
            int finalI = i;
            exec.submit(() -> {
                try {
                    client(ip, port, finalI);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });


        }
    }
}