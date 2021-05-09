package com.example;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author:
 * @date: created in 15:11 2021/4/24
 * @version:
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {
    public static class Score {
        volatile int score;
    }

    static Score math;
    static Score computer;
    static AtomicIntegerFieldUpdater<Score> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Score.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            computer.score++;
            scoreUpdater.getAndIncrement(math);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        math = new Score();
        computer = new Score();
        AtomicIntegerFieldUpdaterDemo updaterDemo = new AtomicIntegerFieldUpdaterDemo();
        Thread thread1 = new Thread(updaterDemo);
        Thread thread2 = new Thread(updaterDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("普通变量的结果："+ computer.score);
        System.out.println("升级后的结果："+ math.score);
    }
}