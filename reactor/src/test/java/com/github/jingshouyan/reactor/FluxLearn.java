package com.github.jingshouyan.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.context.Context;
import reactor.util.retry.Retry;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

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

    @Test
    public void t3() {
        AtomicInteger ai = new AtomicInteger();
        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
            if (ai.incrementAndGet() == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };

        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .transformDeferred(filterAndMap);

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: " + d));
    }


    @Test
    public void t4() {
        Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .map(String::toUpperCase);

        source.subscribe(d -> System.out.println("Subscriber 1: " + d));
        sleep(300);
        source.subscribe(d -> System.out.println("Subscriber 2: " + d));
    }

    @Test
    public void t5() {
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .groupBy(i -> (i % 2) + "")
                        .concatMap(g -> g.defaultIfEmpty(-1) //if empty groups, show them
                                .map(String::valueOf) //map to string
                                .startWith(g.key())) //start with the group's key
        )
                .expectNext("1")
                .expectNext("1", "3", "5", "11", "13")
                .expectNext("0")
                .expectNext("2", "4", "6", "12")
                .verifyComplete();
    }

    @Test
    public void t6() {
        StepVerifier.create(
                Flux.range(1, 10)
                        .window(5, 3) //overlapping windows
                        .concatMap(g -> g.defaultIfEmpty(-1)) //show empty windows as -1
                        .doOnEach(signal -> log.info("each: {}", signal))
        )
                .expectNext(1, 2, 3, 4, 5)
                .expectNext(4, 5, 6, 7, 8)
                .expectNext(7, 8, 9, 10)
                .expectNext(10)
                .verifyComplete();
    }

    @Test
    public void t7() {
        StepVerifier.create(
                Flux.just(8, 1, 3, 5, 2, 4, 6, 11, 15, 12)
                        .windowUntil(i -> i % 2 == 0)
                        .concatMap(g -> g.startWith(0))
        )
                .expectNext(0, 8)
                .expectNext(0, 1, 3, 5, 2) //respectively triggered by odd 1 3 5
                .expectNext(0, 4) // triggered by 11
                .expectNext(0, 6)
                .expectNext(0, 11, 15, 12)
//                .expectNext(0, 15)
//                .expectNext(0, 12) // triggered by 13
                // however, no empty completion window is emitted (would contain extra matching elements)
                .verifyComplete();
    }


    @Test
    public void t8() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap(s -> Mono.subscriberContext()
                        .map(ctx -> s + " " + ctx.get(key)))
                .subscriberContext(ctx -> ctx.put(key, "World"));

        StepVerifier.create(r)
                .expectNext("Hello World")
                .verifyComplete();
    }

    @Test
    public void t9() {
        AtomicInteger ai = new AtomicInteger();
        Mono<Context> mono = Mono.subscriberContext()
                .subscriberContext(ctx -> ctx.put("a", ai.incrementAndGet()))
                .subscriberContext(ctx -> ctx.put("b", ai.incrementAndGet()))
                .subscriberContext(ctx -> ctx.put("c", ai.incrementAndGet()))
                .subscriberContext(ctx -> ctx.put("d", ai.incrementAndGet()));
        mono.subscribe(ctx -> System.out.println(ctx));
    }

    @Test
    public void t10() {
        Mono<String> mono = Mono.just("abc");

        mono.subscribe(s -> log.info("1-> {}", s));
        System.out.println("------------");
        mono = mono
                .doOnSubscribe(subscription -> log.info("a1 start"))
                .doOnSuccess(s -> log.info("a1 end, {}", s));

        mono.subscribe(s -> log.info("2-> {}", s));
        System.out.println("------------");

        mono = mono
                .<String>map(s -> {throw new RuntimeException("222");})
                .doOnSubscribe(subscription -> log.info("a2 start"))
                .doOnSuccess(s -> log.info("a2 end, {}", s))
                .doOnEach(s -> log.info("a2 each, {}", s));

        mono.subscribe(s -> log.info("3-> {}", s));
        System.out.println("------------");
    }
}
