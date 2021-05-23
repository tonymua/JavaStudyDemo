package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo1 {
    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "调用了慢服务！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(50, 50, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
        executorService.shutdown();
    }
}
