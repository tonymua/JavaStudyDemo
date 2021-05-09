package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author:
 * @date: created in 22:05 2021/4/27
 * @version:
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);
        ExecutorService executorService = new ThreadPoolExecutor(8, 16, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(1,10).forEach(i->executorService.submit(()->{
            accumulator.accumulate(i);
            System.out.println(Thread.currentThread().getName()+"..."+accumulator.get());
        }));
        Thread.sleep(2000);
        System.out.println(accumulator.getThenReset());
    }
}