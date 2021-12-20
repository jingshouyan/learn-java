package com.github.jingshouyan.learn.springboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

/**
 * @author 29017
 */
@SpringBootApplication
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ApplicationRunner runner(WebServerApplicationContext ctx) {
        return args -> {
            System.out.println("当前 webServer 实现类: " + ctx
                    .getWebServer().getClass().getName());
        };
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("2当前 webServer 实现类: " + event
                .getWebServer().getClass().getName());
    }
}
