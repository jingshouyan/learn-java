package com.github.jingshouyan.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * list test
 *
 * @author jingshouyan
 * 2021-01-18 19:29
 **/
public class ListTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        Boolean flag = false;
        // a*b的结果是int类型，那么c会强制拆箱成int类型，抛出NPE异常
        Integer result=(flag ? a : c);
    }

    private static List<String> list(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        return list;
    }
}
