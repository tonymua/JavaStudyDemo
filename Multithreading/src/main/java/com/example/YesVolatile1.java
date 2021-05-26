package com.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可以使用volatile的场景 布尔标记位
 */
public class YesVolatile1 implements Runnable {
    volatile boolean flag = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        YesVolatile1 yesVolatile1 = new YesVolatile1();
        Thread thread1 = new Thread(yesVolatile1);
        Thread thread2 = new Thread(yesVolatile1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(yesVolatile1.flag);
        System.out.println(yesVolatile1.realA);
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            realA.incrementAndGet();
            setDone();
        }
    }
    private void setDone() {
        flag = true;
    }
}
