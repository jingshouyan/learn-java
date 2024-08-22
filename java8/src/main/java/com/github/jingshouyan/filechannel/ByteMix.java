package com.github.jingshouyan.filechannel;

/**
 * @author jingshouyan
 * 2022-08-26 10:07
 */
public class ByteMix {
    public static final int BYTE_LEN = 8;
    private final byte offset;

    public ByteMix(int hashcode) {
        offset = (byte) (Math.abs(hashcode) % Byte.MAX_VALUE);
    }

    public byte encode(byte b) {
        return (byte) (b + offset);
    }

    public byte decode(byte b) {
        return (byte) (b - offset);
    }

}
