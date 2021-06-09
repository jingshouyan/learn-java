package com.github.jingshouyan;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.redisson.Redisson;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * redisson
 *
 * @author jingshouyan
 * 2021-05-31 16:04
 **/
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class redisRedissonTest {

    private RedissonClient rc;
    public static final int BATCH_SIZE = 1000;

    @Setup
    public void setup() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        rc = Redisson.create(config);
    }

    private static AtomicLong atomicLong = new AtomicLong();

    @Benchmark
    public void set() {
        String key = "test:" + atomicLong.getAndIncrement();
        rc.getBucket(key).set(atomicLong.toString());
    }

    @Benchmark
    public void batchSet() {
        RBatch batch = rc.createBatch();
        long index = atomicLong.getAndAdd(BATCH_SIZE);
        for (int i = 0; i < BATCH_SIZE; i++) {
            String key = "test:" + index + i;
            batch.getBucket(key).setAsync(index + i);
        }
        batch.execute();
    }

    @TearDown
    public void shutdown() {
        rc.shutdown();
    }

    public static void main(String[] args) throws RunnerException, IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String path = "redisRedissonTest." + now.format(formatter) + ".log";
//        File file = new File(path);
//        file.createNewFile();
        Options options = new OptionsBuilder()
                .include(redisRedissonTest.class.getSimpleName())
                .output(path)
                .build();
        new Runner(options).run();
    }
}
