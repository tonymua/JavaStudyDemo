package com.example;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 必然死锁的例子
 */
public class MustDeadLock implements Runnable {
    public int flag;
    static Object object1 = new Object();
    static Object object2 = new Object();

    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "的flag为" + flag);
        if (flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("线程" + Thread.currentThread().getName() + "获取到了两把锁！");
                }
            }
        }
        if (flag == 2) {
            synchronized (object2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("线程" + Thread.currentThread().getName() + "获取到了两把锁！");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MustDeadLock mustDeadLock1 = new MustDeadLock();
        MustDeadLock mustDeadLock2 = new MustDeadLock();
        mustDeadLock1.flag = 1;
        mustDeadLock2.flag = 2;
        Thread thread1 = new Thread(mustDeadLock1, "thread1");
        Thread thread2 = new Thread(mustDeadLock2, "thread2");
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println(threadInfo);
            }
        }
    }
}
