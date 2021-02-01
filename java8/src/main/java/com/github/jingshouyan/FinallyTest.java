package com.github.jingshouyan;


import lombok.Data;

/**
 * finally
 *
 * @author jingshouyan
 * 2020-10-14 17:20
 **/
public class FinallyTest {

    public static void main(String[] args) {
        String a = get();
        System.out.println("main --"+a);
        A a1 = getA();
        System.out.println("main --"+a1);
    }

    private static String get() {
        String a = "a1";
        try{
            a = "a2";
            return a;
        }finally {
            a = "a3";
            System.out.println(a);
        }
    }

    private static A getA() {
        A a = new A("a1");
        try{
            a.name = "a2";
            return a;
        }finally {
            a.name = "a3";
            System.out.println(a);
        }
    }

    @Data
    static class A {
        private String name;

        public A(String name) {
            this.name = name;
        }
    }
}
