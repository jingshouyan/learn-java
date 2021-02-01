package com.github.jingshouyan.iface;

/**
 * C
 *
 * @author jingshouyan
 * 2020-11-02 14:08
 **/
public class C implements B{

    @Override
    public String name() {
        return "C";
    }

    public static void main(String[] args) {
        Class<?>[] cs = C.class.getInterfaces();
        for (Class<?> c:cs) {
            System.out.println(c);
        }
    }
}
