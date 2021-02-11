package com.github.jingshouyan.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author jingshouyan
 * #date 2021/2/11 16:15
 */
public class BufferTest {

    public static void main(String[] args) {
        String msg = "Hello World! 你好,世界!";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        buf.put(bytes);
        buf.flip();
        byte[] tmp = new byte[bytes.length];
        int i = 0;
        while (buf.hasRemaining()) {
            byte b = buf.get();
            tmp[i] = b;
            i++;
        }
        String received = new String(tmp);
        System.out.println(received);
        buf.clear();
        buf.put(bytes);
        buf.put(bytes);
        System.out.println(buf);
    }
}
