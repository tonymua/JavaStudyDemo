package com.example;

/**
 * @author:
 * @date: created in 15:10 2021/3/20
 * @version:
 */
public class StopThread implements Runnable{
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()&&count<1000){
            System.out.println(Thread.currentThread().getName()+": "+count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
    }
}