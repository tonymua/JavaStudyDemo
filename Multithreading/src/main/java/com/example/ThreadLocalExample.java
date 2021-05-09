package com.example;

import java.text.SimpleDateFormat;

/**
 * @author:
 * @date: created in 19:19 2021/3/30
 * @version:
 */
public class ThreadLocalExample implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> formatThreadLocal =
        ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));
    /*private static final ThreadLocal<SimpleDateFormat> formatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };*/


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample threadLocalExample = new ThreadLocalExample();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(threadLocalExample,""+i);
            Thread.sleep(3000);
            thread.start();
        }
    }

    @Override
    public void run() {
        System.out.println("ThreadName：" + Thread.currentThread().getName() + "---default Formatter："
            + formatThreadLocal.get().toPattern());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatThreadLocal.set(new SimpleDateFormat());
        System.out.println("ThreadName：" + Thread.currentThread().getName() + "---default Formatter："
                + formatThreadLocal.get().toPattern());
    }
}