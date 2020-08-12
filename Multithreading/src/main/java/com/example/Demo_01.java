package com.example;

/**
 * @author:
 * @date: created in 14:49 2020/7/29
 * @version:
 */
public class Demo_01 {
    public static void main(String[] args) {
        System.out.println("main start...");
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("thread run...");
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread end...");
            }
        };
        thread.start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end...");
    }
}