package com.example;

/**
 * @author:
 * @date: created in 16:13 2021/4/9
 * @version:
 */
public class SynchronizedTest {
    public void method1() {
        System.out.println(Thread.currentThread().getName() + "----Method 1 start");
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "----Method 1 execute");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "----Method 1 end");
    }

    public void method2() {
        System.out.println(Thread.currentThread().getName() + "----Method 2 start");
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "----Method 2 execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "----Method 2 end");
    }

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.method2();
            }
        }).start();
    }
}