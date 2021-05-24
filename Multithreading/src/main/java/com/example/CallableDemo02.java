package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.util.StopWatch;

/**
 * @author:
 * @date: created in 19:44 2021/5/11
 * @version:
 */
public class CallableDemo02 {
    static ExecutorService executorService = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    private class Task implements Callable<Integer> {
        Integer productId;

        public Task(Integer productId) {
            this.productId = productId;
        }

        @Override
        public Integer call() throws Exception {
            int price = 0;
            try {
                Thread.sleep((long)(Math.random() * 4000));
                price = (int)(Math.random() * 4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return price;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CallableDemo02 callableDemo02 = new CallableDemo02();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(callableDemo02.getPrices());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        executorService.shutdown();
    }

    private Set<Integer> getPrices() throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        List<Integer> productIdList = new ArrayList<Integer>() {
            {
                add(123);
                add(456);
                add(789);
            }
        };
        for (Integer productId : productIdList) {
            Future<Integer> future = executorService.submit(new Task(productId));
            try {
                try {
                    prices.add(future.get(3, TimeUnit.SECONDS));
                } catch (TimeoutException e) {
//                    e.printStackTrace();
                }
            } catch (ExecutionException e) {
//                e.printStackTrace();
            }
        }
        return prices;
    }
}