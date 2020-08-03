package com.github.jingshouyan.rot13;


public class Rot13UTF16 {

    public static final int B_START = 33;
    public static final int B_END = 125;


    public static final int PLANE1_START = 0x0000_0000;
    public static final int ASCII_STOP = 0x0000_007F;
    public static final int PLANE1_STOP = 0x0000_DBFF;



    private static int ascii(int v) {
        if (v >= B_START && v < B_END) {
            return (B_START + B_END) - v;
        }
        return v;
    }

    public static String encode(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int v = chars[i];
            if (v <= ASCII_STOP) {
                chars[i] = (char) ascii(v);
            } else if(v <= PLANE1_STOP){
                chars[i] = (char) ((PLANE1_STOP + ASCII_STOP + 1) - v);
            }
        }
        return new String(chars);
    }


}
