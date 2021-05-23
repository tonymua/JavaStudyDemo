package com.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RunDemo3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
            new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch downLatch1 = new CountDownLatch(5);
        CountDownLatch downLatch2 = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            int finalI = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(finalI + "号运动员准备完毕，等待裁判员的发令枪");
                        downLatch2.await();
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println(finalI + "号运动员完成了比赛");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        downLatch1.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("5秒准备时间已过，发令枪响，比赛开始！");
        downLatch2.countDown();
        System.out.println("等待5个运动员都跑完....");
        downLatch1.await();
        System.out.println("所有人都跑完了，比赛结束");
        executorService.shutdown();
    }
}
