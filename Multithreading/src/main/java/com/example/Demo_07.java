package com.example;

import java.util.concurrent.*;

/**
 * @author:
 * @date: created in 17:04 2020/7/29
 * @version:
 */
public class Demo_07 {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        // ExecutorService executorService = Executors.newFixedThreadPool(4);
        ExecutorService executorService = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), Executors.privilegedThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Task("" + i));
        }
        // 关闭线程池
        executorService.shutdown();
    }
}

class Task implements Runnable {

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task" + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end task" + name);
    }
}
