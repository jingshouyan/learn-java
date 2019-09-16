package com.github.jingshouyan.forkjoin;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jingshouyan
 * #date 2019/8/21 14:32
 */

public class RecursiveTest {
    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @SneakyThrows
    public static void main(String[] args) {
        List<String> messages = IntStream.range(0,1000000)
                .mapToObj(i-> "message:"+i)
                .collect(Collectors.toList());

        StringAction stringAction = new StringAction(messages);
        forkJoinPool.submit(stringAction);

        Thread.sleep(1000000);
    }
}
