package com.github.jingshouyan.list;

import java.util.*;

/**
 * list test
 *
 * @author jingshouyan
 * 2021-01-18 19:29
 **/
public class ListTest {

    public static void main(String[] args) {
//        Integer a = 1;
//        Integer b = 2;
//        Integer c = null;
//        Boolean flag = false;
//        // a*b的结果是int类型，那么c会强制拆箱成int类型，抛出NPE异常
//        Integer result=(flag ? a : c);

        t1();
    }

    private static List<String> list() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        return list;
    }

    private static void t1() {
        String[] arr1 = {"a", "b", "3", "c", "b"};
        List<String> list = new ArrayList<>();
        list.add("d");
        Collections.addAll(list, arr1);
        System.out.println(list);

        Set<String> set = new HashSet<>();
        set.add("d");
        Collections.addAll(set, arr1);
        System.out.println(set);
    }
}
