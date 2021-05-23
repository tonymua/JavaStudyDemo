package com.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RunDemo1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
            new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println(finalI + "号运动员完成了比赛");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("等待5个运动员都跑完....");
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有人都跑完了，比赛结束");
    }
}
