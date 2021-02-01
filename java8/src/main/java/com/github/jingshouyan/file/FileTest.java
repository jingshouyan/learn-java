package com.github.jingshouyan.file;

import java.io.*;

public class FileTest {

    public static final String FILE = "d:/test.txt";
    public static final int HEAD_LEN = 256;
    public static final int CHUCK_LEN = 264;
    public static final int CHUCK_SIZE = 9;

    public static void main(String[] args) throws IOException {
        File file = new File(FILE);

        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            byte[] header = new byte[HEAD_LEN];
            raf.write(header);
            for (int i = 0; i < CHUCK_SIZE; i++) {
                byte[] chuck = new byte[CHUCK_LEN];
                for (int j = 0; j < CHUCK_LEN; j++) {
                    chuck[j] = (byte) (i + 33);
                }
                raf.write(chuck);
            }
            for (int i = 0; i < HEAD_LEN; i++) {
                header[i] = 'c';
            }
            raf.seek(0);
            raf.write(header);

        }

    }
}
