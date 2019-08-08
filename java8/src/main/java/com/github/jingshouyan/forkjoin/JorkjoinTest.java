package com.github.jingshouyan.forkjoin;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jingshouyan
 * #date 2019/8/8 11:21
 */

@Slf4j
public class JorkjoinTest implements Runnable {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool(20);
    private static final AtomicInteger i = new AtomicInteger();
    private static final AtomicInteger b = new AtomicInteger();
    @SneakyThrows
    public static void main(String[] args) {
        Runnable runnable = new JorkjoinTest();
        while (true){
            for (int j = 0; j < 1000; j++) {
                forkJoinPool.execute(runnable);
            }
//            forkJoinPool.awaitTermination(20,TimeUnit.SECONDS);
            forkJoinPool.awaitQuiescence(100000, TimeUnit.SECONDS);
        }
//        log.info("end");
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            int sleep = random.nextInt(10);
//            Thread.sleep(sleep);
            int x = i.getAndIncrement();
            log.info("print {}", x);
            if (x % 3 == 0) {
                forkJoinPool.execute(this);
            }
//            if(sleep == 180){
//                int xx = b.incrementAndGet();
//                log.warn("error time : {}",xx);
//                throw new RuntimeException("xxx");
//            }
        } catch (Throwable e) {
            log.error("error:", e);
        }
    }
}
