package com.github.jingshouyan.redismysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author jingshouyan
 * 2022-08-15 14:56
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate tmp;

    @Test
    public void testTimeCost() throws InterruptedException {
        ValueOperations<String, String> vopt = tmp.opsForValue();
//        vopt.get("abc");
        Thread.sleep(1000);
        Utils.show("2");
        Utils.show("1");
        for (int i = 0; i < 10; i++) {
//            vopt.set("test:" + i, "test-value-" + i);
            vopt.get("test:" + i);
            Utils.show("1");
        }
        Utils.show("2");
    }

    @Test
    public void testStart() {

    }
}
