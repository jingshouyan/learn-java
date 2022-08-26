package com.github.jingshouyan.redismysql;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jingshouyan
 * 2022-08-15 15:08
 */
@Slf4j
public class Utils {

    private static final Map<String, Long> m = new HashMap<>();

    public static void show(String key) {
        long now = System.nanoTime();
        Long t = m.get(key);
        if (t == null) {
            log.info("[{}] first call", key);
        } else {
            log.info("[{}] use {}ms", key, (now - t) / 1e6);
        }
        m.put(key, now);
    }

}
