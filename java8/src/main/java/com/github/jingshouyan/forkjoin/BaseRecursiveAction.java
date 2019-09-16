package com.github.jingshouyan.forkjoin;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.function.Consumer;

/**
 * @author jingshouyan
 * #date 2019/8/21 14:03
 */

public class BaseRecursiveAction<T> extends RecursiveAction {
    private final List<T> jobs;
    private final int start;
    private final int end;
    private final int threshold;
    private final Consumer<T> consumer;

    public BaseRecursiveAction(List<T> jobs, Consumer<T> consumer, int threshold) {
        this(jobs, consumer, threshold, 0, jobs.size());
    }

    private BaseRecursiveAction(List<T> jobs, Consumer<T> consumer, int threshold, int start, int end) {
        this.jobs = jobs;
        this.consumer = consumer;
        this.threshold = threshold;
        this.start = start;
        this.end = end;
    }

    @Override
    @SneakyThrows
    protected void compute() {
        try {
            if ((end - start) <= threshold) {
                for (int i = start; i < end; i++) {
                    consumer.accept(jobs.get(i));
                }
            } else {
                int middle = (start + end) / 2;
                BaseRecursiveAction<T> left = new BaseRecursiveAction<>(jobs, consumer, threshold, start, middle);
                BaseRecursiveAction<T> right = new BaseRecursiveAction<>(jobs, consumer, threshold, middle, end);
                invokeAll(left, right);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
