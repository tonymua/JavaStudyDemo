package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.StopWatch;

/**
 * @author:
 * @date: created in 21:49 2021/4/25
 * @version:
 */
public class AtomicLongDemo {
    static class Task implements Runnable {
        private final AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.incrementAndGet();
            System.out.println(Thread.currentThread().getName()+"..."+counter.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService poolExecutor = new ThreadPoolExecutor(20, 40, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.AbortPolicy());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            poolExecutor.submit(new Task(counter));
        }
        Thread.sleep(2000);
        System.out.println("result:"+counter.get());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}