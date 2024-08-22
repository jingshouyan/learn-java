package com.github.jingshouyan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class RedisTest {

    @Resource
    private StringRedisTemplate srt;

    public static final String KEY = "redisson:big:";

    public static final AtomicInteger l = new AtomicInteger();

    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

    public void generate() {
        String key = KEY + 0;

//            Map<String,String> map = new HashMap<>();
//
//            for (int i = 0; i < 1000; i++) {
//                String uuid = UUID.randomUUID().toString().toLowerCase().replaceAll("-","");
//                map.put(uuid,uuid);
//            }
//            srt.opsForHash().putAll(key,map);



        Set<Object> keys = srt.opsForHash().keys(key);
        log.info("{} {}",key,keys.size());

    }

}
