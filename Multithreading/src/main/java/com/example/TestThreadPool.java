package com.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

/**
 * 线程池
 *
 * @author: lwy
 * @date: created in 15:06 2020/11/3
 * @version: 1.0.0
 */
public class TestThreadPool {
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 0L,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.AbortPolicy());

    private static class testTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        testTask testTask = new testTask();
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.submit(testTask);
        }
        threadPoolExecutor.shutdown();
    }
}
