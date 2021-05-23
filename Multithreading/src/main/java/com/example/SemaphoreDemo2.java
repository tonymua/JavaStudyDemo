package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StopWatch;

public class SemaphoreDemo2 {
    static Semaphore semaphore = new Semaphore(5);
    static ThreadLocal<StopWatch> stopWatchThreadLocal = ThreadLocal.withInitial(() -> new StopWatch());

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取到许可证，开始执行任务！");
            StopWatch stopWatch = stopWatchThreadLocal.get();
            stopWatch.start();
            try {
                Thread.sleep(3000);
                stopWatch.stop();
                System.out.println("慢服务执行完毕，耗时：" + stopWatch.getTotalTimeMillis() + "---"
                    + Thread.currentThread().getName() + "释放了许可证！");
                semaphore.release();
                stopWatchThreadLocal.remove();
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
