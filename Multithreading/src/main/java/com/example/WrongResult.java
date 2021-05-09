package com.example;

/**
 * @author:
 * @date: created in 18:38 2021/3/21
 * @version:
 */
public class WrongResult implements Runnable {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        WrongResult wrongResult = new WrongResult();
        Thread t1 = new Thread(wrongResult);
        Thread t2 = new Thread(wrongResult);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    @Override
    public synchronized void run() {
        for (int j = 0; j < 10000000; j++) {
            count++;
        }
    }
}