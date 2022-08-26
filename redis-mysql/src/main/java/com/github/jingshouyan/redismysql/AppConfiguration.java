package com.github.jingshouyan.redismysql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jingshouyan
 * 2022-08-15 14:59
 */
@Configuration
public class AppConfiguration {

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory rcf) {
        StringRedisTemplate template = new StringRedisTemplate(rcf);
        template.opsForValue().get("test");
        return template;
    }
}
