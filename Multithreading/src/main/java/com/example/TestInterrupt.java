package com.example;

/**
 * 中断
 *
 * @author: lwy
 * @date: created in 9:32 2020/11/5
 * @version: 1.0.0
 */
public class TestInterrupt {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread RUN!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();
        System.out.println("Main RUN!");
    }
}