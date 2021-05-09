package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author:
 * @date: created in 22:39 2021/4/27
 * @version:
 */
public class ThreadLocalDemo01 {
    public static ExecutorService executorService = new ThreadPoolExecutor(16, 32, 10, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(1, 1000).forEach(i -> executorService.submit(() -> {
            String date = new ThreadLocalDemo01().date(i);
            System.out.println(Thread.currentThread().getName() + ":" + date);
        }));
    }
}