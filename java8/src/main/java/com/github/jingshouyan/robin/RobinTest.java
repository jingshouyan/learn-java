package com.github.jingshouyan.robin;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * @author jingshouyan
 * 2021-08-03 16:48
 **/
@Slf4j
public class RobinTest {

    public static final int SIZE = 4;
    public static final int LOOP = 300_000_000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(4);
        CountDownLatch cdl = new CountDownLatch(LOOP);
        SmoothWeightRoundRobin roundRobin = new SmoothWeightRoundRobin();
        Map<String, LongAdder> map = new HashMap<>(SIZE);
        for (int i = 1; i <= SIZE; i++) {
            SmoothServer svr = new SmoothServer();
            svr.setName("svr-" + i);
            svr.setWeight(i * i);
            svr.setCurWeight(0);
            roundRobin.add(svr);
            map.put(svr.getName(), new LongAdder());
        }
        long start = System.currentTimeMillis();
        Runnable runnable = () -> {
            SmoothServer svr = roundRobin.get();
            LongAdder ai = map.get(svr.getName());
            ai.increment();
             cdl.countDown();
        };
//        for (int i = 0; i < LOOP; i++) {
////         exec.execute(runnable);
////            runnable.run();
//        }
        IntStream.range(0,LOOP).parallel()
                .forEach(i -> runnable.run());

        cdl.await();
//        Thread.sleep(500);
        long end = System.currentTimeMillis();
        int total = map.values().stream().mapToInt(LongAdder::intValue).sum();
        log.info("use: {}ms,total: {},result: {}", end - start, total, map);
    }


}
