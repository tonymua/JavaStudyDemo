package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author:
 * @date: created in 22:52 2021/4/27
 * @version:
 */
public class ThreadLocalDemo04 {
    public static Lock lock = new ReentrantLock();
    public static ExecutorService executorService = new ThreadPoolExecutor(16, 32, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String format = null;
        lock.lock();
        format = simpleDateFormat.format(date);
        lock.unlock();
        return format;
    }

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(1, 1000).forEach(i -> executorService.submit(() -> {
            String date = new ThreadLocalDemo04().date(i);
            System.out.println(Thread.currentThread().getName() + ":" + date);
        }));
        Thread.sleep(2000);
        executorService.shutdown();
    }
}