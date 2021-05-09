package com.example;

import lombok.SneakyThrows;

/**
 * @author:
 * @date: created in 22:18 2021/3/21
 * @version:
 */
public class MayDeadLock {
    Object lock1 = new Object();
    Object lock2 = new Object();
    public void thread1() throws InterruptedException {
        synchronized (lock1){
            Thread.sleep(500);
            synchronized (lock2){
                System.out.println("线程1成功拿到两把锁");
            }
        }
    }
    public void thread2() throws InterruptedException {
        synchronized (lock2){
            Thread.sleep(500);
            synchronized (lock1){
                System.out.println("线程2成功拿到两把锁");
            }
        }
    }

    public static void main(String[] args) {
        MayDeadLock deadLock = new MayDeadLock();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                deadLock.thread1();
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                deadLock.thread2();
            }
        }).start();
    }
}