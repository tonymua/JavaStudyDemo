package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureDemo {
    static class SlowTask implements Callable {
        @Override
        public Object call() throws Exception {
            Thread.sleep(5000);
            return "速度慢的任务" + Thread.currentThread().getName();
        }
    }

    static class FastTask implements Callable {
        @Override
        public Object call() throws Exception {
            return "速度快的任务" + Thread.currentThread().getName();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 10, TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Future future;
            if (i == 0 || i == 1) {
                future = executorService.submit(new SlowTask());
            } else {
                future = executorService.submit(new FastTask());
            }
            futures.add(future);
        }
        for (int i = 0; i < 4; i++) {
            Future future = futures.get(i);
            try {
                String result = (String)future.get();
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
