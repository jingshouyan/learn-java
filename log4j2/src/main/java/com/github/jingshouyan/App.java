package com.github.jingshouyan;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingshouyan
 * 2021-12-15 16:20
 */
@Slf4j
public class App implements ApplicationRunner {

    public static void main(String[] args) {
//        System.setProperty("logging.level.com.github.jingshouyan","debug");
        SpringApplication.run(App.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            Thread.sleep(1000L);
            log.info("name is : {}, {}", "name", "des");
        }

    }
}
