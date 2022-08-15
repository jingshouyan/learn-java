package com.github.jingshouyan;

/**
 * @author jingshouyan
 * 2022-05-14 20:25
 */
public class ArrayTest {

    public static void main(String[] args) {
        double[] a = new double[2];
        double[] b = a;
        a[0] = 5;
        System.out.println(b[0]);
        System.out.println(-1 % 16384);
    }
}
