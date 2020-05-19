package com.github.jingshouyan.reactor;

import lombok.SneakyThrows;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;

/**
 * @author jingshouyan
 * #date 2020/5/19 11:08
 */
public class FluxTest {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> flux = Flux.range(0, 1000)
//                .subscribeOn(Schedulers.boundedElastic())
                .doOnEach(i -> System.out.println("do on each: " + i))
                .doOnRequest(i -> System.out.println("request: "+ i ))
                .limitRate(75)
                .window(5)
                .concatMap(f -> f,8)

                ;
        SimpleSubscriber<Integer> simpleSubscriber = new SimpleSubscriber<>();
        flux.subscribe(simpleSubscriber);

//        Flux.merge(Flux.interval(Duration.ofSeconds(1)).take(5), Flux.interval(Duration.ofSeconds(1)).take(5))
//                .toStream()
//                .forEach(System.out::println);
//        Flux.mergeSequential(Flux.interval(Duration.ofSeconds(1)).take(5), Flux.interval(Duration.ofSeconds(1)).take(5))
//                .toStream()
//                .forEach(System.out::println);
//        Disposable disposable = flux
//                .map(i -> {
//                    if(i<7){
//                        return i;
//                    }
//                    throw new RuntimeException("gte "+i);
//                })
////                .doOnEach(i -> System.out.println("do on each2: " + i))
//                .subscribe(
//                i -> System.out.println("sub: " + i)
//                , error -> System.err.println("Error: " + error)
//                , () -> System.out.println("Done")
//                , sub -> sub.request(10)
//        );

//        Disposable disposable1 =Flux.interval(Duration.ofSeconds(1))
//                .doOnEach(i -> System.out.println("interval do on each: " + i))
//                .subscribe(i-> System.out.println("interval: "+i));
//        Thread.sleep(3000);
////        disposable.dispose();
//        Disposable.Swap swap = Disposables.swap();
//        swap.replace(disposable1);
//        swap.dispose();
//        swap.replace(disposable);
        Thread.sleep(3000);
    }

    public static class SimpleSubscriber<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("SimpleSubscriber Subscribed");
            subscription.request(40);
        }

        @SneakyThrows
        @Override
        protected void hookOnNext(T value) {

            System.out.println("SimpleSubscriber hookOnNext " + value);
//            cancel();
//            Thread.sleep(1000);
//            request(2);
        }

        @Override
        protected void hookFinally(SignalType type) {
            System.out.println("SimpleSubscriber hookFinally" + type);
            super.hookFinally(type);
        }
    }
}
