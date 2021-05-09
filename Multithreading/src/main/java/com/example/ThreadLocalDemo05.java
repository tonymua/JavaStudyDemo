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
 * @date: created in 22:53 2021/4/28
 * @version:
 */
public class ThreadLocalDemo05 {
    static ThreadLocal<SimpleDateFormat> formatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }
    };

    public static ExecutorService executorService = new ThreadPoolExecutor(16, 32, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = formatThreadLocal.get();
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(1, 1000).forEach(i -> executorService.submit(() -> {
            String date = new ThreadLocalDemo05().date(i);
            System.out.println(Thread.currentThread().getName() + ":" + date);
        }));
        Thread.sleep(2000);
        executorService.shutdown();
    }
}