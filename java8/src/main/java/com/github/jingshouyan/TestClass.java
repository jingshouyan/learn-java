package com.github.jingshouyan;

/**
 * test
 *
 * @author jingshouyan
 * 2021-05-26 17:59
 **/
public class TestClass {
    private int m;

    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
