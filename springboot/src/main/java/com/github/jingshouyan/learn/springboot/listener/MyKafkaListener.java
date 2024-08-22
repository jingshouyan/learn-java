package com.github.jingshouyan.learn.springboot.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyKafkaListener {

    @KafkaListener(topics = "topic-qwe1", groupId = "demo-app", concurrency = "10")
    public void listen(ConsumerRecord<?, ?> record) {
        // 处理消费记录
        log.info("消费记录：{}", record);
    }

}
