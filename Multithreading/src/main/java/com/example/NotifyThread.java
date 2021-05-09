package com.example;

import lombok.SneakyThrows;

/**
 * @author:
 * @date: created in 19:38 2021/3/21
 * @version:
 */
public class NotifyThread extends Thread {
    private Object lock;

    public NotifyThread(Object lock) {
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        super.run();
        synchronized (lock) {
            System.out.println("Thread wait");
            lock.wait();
            System.out.println("Thread run");
        }

    }

    public static void main(String[] args) {
        Object nlock = new Object();
        NotifyThread t = new NotifyThread(nlock);
        t.start();
        try {
            System.out.println("before sleep");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after sleep");
        synchronized (nlock) {
            nlock.notify();
            System.out.println("notify Thread");
        }
    }
}