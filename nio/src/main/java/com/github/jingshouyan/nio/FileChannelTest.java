package com.github.jingshouyan.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
        File f2 = new File("pom.xml.log");
        FileOutputStream os = new FileOutputStream(f2);
        FileChannel oc = os.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(CAPACITY);
//        while(ic.read(buf)>0) {
//            if (buf.hasRemaining()) {
//                byte[] tmp = new byte[buf.remaining()];
//                Arrays.fill(tmp,(byte)65);
//                buf.put(tmp);
//            }
//            buf.flip();
//            oc.write(buf);
//            buf.clear();
//        }
        for (int i = 0; ic.transferTo(i, COUNT, oc) == COUNT; i += COUNT) {
            Thread.sleep(100);
        }
        ;

        os.close();
        oc.close();
        ic.close();
        is.close();
    }
}
