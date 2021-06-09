package com.github.jingshouyan;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * counted loop
 *
 * @author jingshouyan
 * 2021-05-26 14:13
 **/
public class CountedLoop {

//    private static final AtomicInteger num = new AtomicInteger();
    public volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000_000; i++) {
//                num.getAndAdd(1);
                num ++;
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num is :" + num);
    }

}
