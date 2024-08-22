package com.github.jingshouyan.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;

public class CacheTest {

    Cache<Long,String> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .initialCapacity(10)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, String>() {
                @Override
                public String load(@NonNull Long key) throws Exception {
                    System.out.println("load:" + key);
                    return String.valueOf(key);
                }

            });
}
