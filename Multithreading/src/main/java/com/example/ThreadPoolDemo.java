package com.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:
 * @date: created in 16:22 2021/3/27
 * @version:
 */
public class ThreadPoolDemo {
    // 用固定线程数的线程池执行10000个任务 
    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("ThreadName:" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,10,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(8),new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(new Task());
        }
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName());
    }
}