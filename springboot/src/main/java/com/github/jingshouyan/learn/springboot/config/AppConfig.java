package com.github.jingshouyan.learn.springboot.config;

import com.github.jingshouyan.learn.springboot.bean.TestBean;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * @author jingshouyan
 * 2021-12-23 16:07
 */
@Configuration
public class AppConfig implements ApplicationRunner {

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @Bean
    public TestBean<String> stringTestBean() {
        return TestBean.<String>builder().data("张三").build();
    }

    @Bean
    public TestBean<Long> longTestBean() {
        return TestBean.<Long>builder().data(111L).build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        NewTopic newTopic = new NewTopic("topic-qwe1", 5, (short) 1);
        kafkaAdmin.createOrModifyTopics(newTopic);
    }
}
