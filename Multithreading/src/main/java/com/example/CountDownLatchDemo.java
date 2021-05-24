package com.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StopWatch;

/**
 * @author:
 * @date: created in 20:15 2021/5/11
 * @version:
 */
public class CountDownLatchDemo {
    static ExecutorService executorService = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    private class Task implements Runnable {
        Integer productId;
        Set<Integer> prices;
        CountDownLatch countDownLatch;

        public Task(Integer productId, Set<Integer> prices, CountDownLatch countDownLatch) {
            this.productId = productId;
            this.prices = prices;
            this.countDownLatch = countDownLatch;
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
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(countDownLatchDemo.getPrices());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        executorService.shutdown();
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.submit(new Task(123, prices, countDownLatch));
        executorService.submit(new Task(456, prices, countDownLatch));
        executorService.submit(new Task(789, prices, countDownLatch));
        countDownLatch.await(3,TimeUnit.SECONDS);
        return prices;
    }
}