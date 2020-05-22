package com.github.jingshouyan.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

/**
 * @author jingshouyan
 * #date 2020/5/20 13:54
 */
@Slf4j
public class FluxLearn {

    @Test
    public void fluxCreate() {
        Flux<Integer> flux = Flux.<Integer>create(fluxSink -> {
                    for (int i = 0; i < 100; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                },
                FluxSink.OverflowStrategy.DROP)
                .subscribeOn(Schedulers.elastic())
                .doOnEach(signal -> log.info("{}", signal));

        flux.subscribe(i -> {
            sleep(1000);
            log.warn("i : {}", i);
        });

        sleep(10000);
    }

    @SneakyThrows
    private static void sleep(long i) {
        Thread.sleep(i);
    }


    @Test
    public void fluxError() {
        Flux<String> stringFlux = Flux.range(0, 30)
//                .subscribeOn(Schedulers.parallel())
                .map(i -> 10 - i)
//                .doOnEach(signal -> log.info("each int: " + signal))
//                .flatMap(i ->
//                                Flux.create(
//                                        (fluxSink) -> fluxSink.next(String.format("100 / %d = %d", i, (100 / i)))
//                                                .complete()
//                                )
////                                .onErrorReturn("error !!!")
//                )
                .map(i -> String.format("100 / %d = %d", i, (100 / i)))
//                .onErrorResume(e -> Flux.just("on error " + e))
//                .onErrorReturn("/ by zero")
                .doOnEach(signal -> log.info("each str: " + signal))
                .doFinally((SignalType signalType) -> {
                    log.info("type: {}", signalType);
                });

        stringFlux
                .retry(2)
                .elapsed()
                .subscribe(s -> log.info("{}", s));
        sleep(100);
    }


    @Test
    public void fluxRetry() {
        Flux<String> flux = Flux
                .<String>error(new IllegalArgumentException())
                .doOnError(System.out::println)
                .retryWhen(Retry.from(companion ->
                        companion.take(3)));

        flux.subscribe();
    }


}
