package com.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Condition实现简易版阻塞队列
 */
public class MyBlockingQueueForCondition {
    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public MyBlockingQueueForCondition(int maxSize) {
        this.max = maxSize;
        queue = new LinkedList();
    }

    public void put(Object object) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(object);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Object item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
