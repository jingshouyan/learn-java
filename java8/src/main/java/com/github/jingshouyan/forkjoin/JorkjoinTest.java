package com.github.jingshouyan.forkjoin;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jingshouyan
 * #date 2019/8/8 11:21
 */

@Slf4j
public class JorkjoinTest implements Runnable {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private static final AtomicInteger i = new AtomicInteger();
    private static final AtomicInteger b = new AtomicInteger();
    @SneakyThrows
    public static void main(String[] args) {
        forkJoinTask();
        log.info("task end");
        for (int j = 0; j < 1000000; j++) {
            Thread.sleep(1000);
            int k  = forkJoinPool.getActiveThreadCount();
            long q = forkJoinPool.getQueuedTaskCount();
            int p = forkJoinPool.getParallelism();
            int x = forkJoinPool.getPoolSize();
            int y = forkJoinPool.getRunningThreadCount();
            long z = forkJoinPool.getStealCount();
            log.info("poll getActiveThreadCount:{}," +
                    "getQueuedTaskCount:{}," +
                    "getParallelism:{}," +
                    "getPoolSize:{}," +
                    "getRunningThreadCount:{}," +
                    "getStealCount:{}",k,q,p,x,y,z);
        }
        Thread.sleep(1000000);
    }

    public static void forkJoinExec(){
        Runnable runnable = new JorkjoinTest();
//        while (true){
            for (int j = 0; j < 1000000; j++) {
                forkJoinPool.execute(runnable);
            }
//            forkJoinPool.awaitQuiescence(100000, TimeUnit.SECONDS);
//        }
    }

    @Override
    @SneakyThrows
    public void run() {
        Thread.sleep(1000);
//        log.info("JorkjoinTest run");
    }

    public static void forkJoinTask() {
        List<String> messages = IntStream.range(0,1000000)
                .mapToObj(i-> "message:"+i)
                .collect(Collectors.toList());
        SendMessageTask task = new SendMessageTask(0,messages.size(),messages);
        try{
            forkJoinPool.submit(task);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void forkJoinTask2() {
        List<String> messages = IntStream.range(0,1000000)
                .mapToObj(i-> "message:"+i)
                .collect(Collectors.toList());
        VrvForJoinTask vrvForJoinTask = new VrvForJoinTask(messages);
        try{
            forkJoinPool.submit(vrvForJoinTask);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class VrvForJoinTask extends RecursiveAction {
        List<String> userIds;
        String userId;
        public VrvForJoinTask(List<String> userIds) {
            this.userIds = userIds;
        }

        public VrvForJoinTask(String userId) {
            this.userId = userId;
        }

        @Override
        @SneakyThrows
        protected void compute() {
            int size = 5;
            if(userIds !=null && userIds.size()>size){
                List<VrvForJoinTask> taskList = new ArrayList<>(userIds.size());
                for(List<String> li: Lists.partition(userIds, size)){
                    VrvForJoinTask task = new VrvForJoinTask(li);
                    task.fork();
                    taskList.add(task);
                }
                for(VrvForJoinTask t:taskList){
                    t.join();
                }
            }else{
                for(String userId:userIds){
                    Thread.sleep(1000);
                }
            }
        }

    }

    public static class SendMessageTask extends RecursiveAction {

        private final int THRESHOLD = 1;
        private int start;
        private int end;
        private List<String> messages;

        public SendMessageTask(int start,int end,List<String> messages) {
            this.start = start;
            this.end = end;
            this.messages = messages;
        }

        @Override
        protected void compute() {
            if((end-start)<= THRESHOLD){
                for (int i = start;i<end;i++){
                    action(messages.get(i));
                }
            } else {
                int middle = (start + end) / 2;
                SendMessageTask left = new SendMessageTask(start,middle,messages);
                SendMessageTask right = new SendMessageTask(middle,end,messages);
//                left.fork();
//                right.fork();
//                left.join();
//                right.join();
                invokeAll(left,right);
            }
        }

        @SneakyThrows
        private void action(String msg) {
            Thread.sleep(1000);
//            log.info(msg);
        }
    }
}
