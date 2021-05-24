package com.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StopWatch;

/**
 * @author:
 * @date: created in 19:21 2021/5/11
 * @version:
 */
public class ThreadPoolDemo02 {
    static ExecutorService executorService = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    private class Task implements Runnable {
        Integer productId;
        Set<Integer> prices;

        public Task(Integer productId, Set<Integer> prices) {
            this.productId = productId;
            this.prices = prices;
        }

        @Override
        public void run() {
            int price = 0;
            try {
                Thread.sleep((long)(Math.random() * 4000));
                price = (int)(Math.random() * 4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolDemo02 threadPoolDemo02 = new ThreadPoolDemo02();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(threadPoolDemo02.getPrices());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        executorService.shutdown();
    }

    private Set<Integer> getPrices() throws InterruptedException {
        // AtomicIntegerArray prices = new AtomicIntegerArray(5);
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        executorService.submit(new Task(123, prices));
        executorService.submit(new Task(456, prices));
        executorService.submit(new Task(789, prices));
        Thread.sleep(3000);
        return prices;
    }
}