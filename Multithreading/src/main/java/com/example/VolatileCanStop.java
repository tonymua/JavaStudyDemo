package com.example;

/**
 * @author:
 * @date: created in 16:04 2021/3/20
 * @version:
 */
public class VolatileCanStop implements Runnable {
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        while (!canceled && num < 1000000) {
            if (num % 10 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + num + "是10的倍数");
            }
            num++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileCanStop canStop = new VolatileCanStop();
        Thread thread = new Thread(canStop);
        thread.start();
        Thread.sleep(3000);
        canStop.canceled = true;
    }
}