package com.example;

/**
 * @author:
 * @date: created in 15:01 2020/7/29
 * @version:
 */
public class Demo_02 {
    public static void main(String[] args)
        throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("hello!");
            }
        };
        System.out.println("start!");
        thread.start();
        // main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行
        thread.join();
        System.out.println("end!");
    }
}