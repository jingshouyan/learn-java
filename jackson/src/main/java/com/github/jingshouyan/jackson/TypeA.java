package com.github.jingshouyan.jackson;

/**
 * enum
 *
 * @author jingshouyan
 * 2020-11-17 09:39
 **/
public enum TypeA {

    /**
     *
     */
    A("A--", "AAA"),
    B("B++", "BBB");

    private String name;
    private String attr;

    TypeA(String name, String attr) {
        this.name = name;
        this.attr = attr;
    }

}
