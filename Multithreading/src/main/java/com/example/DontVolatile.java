package com.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * a++ 不适合使用volatile
 */
public class DontVolatile implements Runnable {
    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        DontVolatile dontVolatile = new DontVolatile();
        Thread thread1 = new Thread(dontVolatile);
        Thread thread2 = new Thread(dontVolatile);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(dontVolatile.a);
        System.out.println(dontVolatile.realA);
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
