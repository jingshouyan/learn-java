package com.github.jingshouyan.forkjoin;

/**
 * @author jingshouyan
 * #date 2020/3/28 15:47
 */
public class SizeTest {

    public static void main(String[] args) {
        toString(-2137128701);
    }

    public static void toString(int i){
        String hex = Integer.toString(i,16);
        System.out.println(hex);
        char[] chars = int2Chars(i,4);
        String s = new String(chars);
        System.out.println(s);
    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;

    }
    public static char[] int2Chars(int value, int len) {
        char[] b = new char[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (char) ((value >> 8 * i) & 0xff);
        }
        return b;

    }
}
