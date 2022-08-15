package com.github.jingshouyan.learn.springboot.config;

import com.github.jingshouyan.learn.springboot.bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingshouyan
 * 2021-12-23 16:07
 */
@Configuration
public class AppConfig {

    @Bean
    public TestBean<String> stringTestBean() {
        return TestBean.<String>builder().data("张三").build();
    }

    @Bean
    public TestBean<Long> longTestBean() {
        return TestBean.<Long>builder().data(111L).build();
    }
}
