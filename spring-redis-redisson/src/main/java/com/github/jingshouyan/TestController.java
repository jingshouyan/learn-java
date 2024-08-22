package com.github.jingshouyan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {


    public static final ExecutorService ex = new ThreadPoolExecutor(32,32,0L,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy()
    );
    public boolean run = false;

    @Autowired
    private RedisTest test;

    @GetMapping("/test")
    public Object test(){

        run = !run;
        if (!run) {
            return false;
        }

        Thread t2  = new Thread(() -> {
            while (run) {
                try {
                    ex.execute(test::generate);
                } catch (Exception ignored) {

                }

            }
        });
        t2.start();
        return run;
    }
}
