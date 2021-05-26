package com.github.jingshouyan.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author jingshouyan
 * #date 2021/2/11 16:37
 */
public class FileChannelTest {

    public static final int CAPACITY = 1024;
    public static final int COUNT = 20;


    public static void main(String[] args) throws Exception {
        File f1 = new File("pom.xml");
        FileInputStream is = new FileInputStream(f1);
        FileChannel ic = is.getChannel();
        RandomAccessFile f3 = new RandomAccessFile("pom.xml.log","rw");
        f3.getChannel();
        File f2 = new File("pom.xml.log");
        FileOutputStream os = new FileOutputStream(f2);
        FileChannel oc = os.getChannel();
//        ByteBuffer buf = ByteBuffer.allocate(CAPACITY);
        ByteBuffer b1 = ByteBuffer.allocate(CAPACITY);
        ByteBuffer b2 = ByteBuffer.allocate(CAPACITY);
        ByteBuffer b3 = ByteBuffer.allocate(CAPACITY);
        ByteBuffer[] buffers = new ByteBuffer[]{b1, b2, b3};
        long read = 0;
        long sum = 0;
        while ((read = ic.read(buffers)) > 0) {
            sum += read;
            Arrays.stream(buffers)
                    .forEach(System.out::println);
            Arrays.stream(buffers).forEach(Buffer::flip);
            oc.write(buffers);
            Arrays.stream(buffers).forEach(Buffer::clear);
        }
        System.out.println(sum);

//        for (int i = 0; ic.transferTo(i, COUNT, oc) == COUNT; i += COUNT) {
//            Thread.sleep(100);
//        }


        os.close();
        oc.close();
        ic.close();
        is.close();
    }
}
